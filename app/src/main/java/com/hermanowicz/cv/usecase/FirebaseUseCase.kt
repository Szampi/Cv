package com.hermanowicz.cv.usecase

import com.google.firebase.firestore.FirebaseFirestore
import com.hermanowicz.cv.model.FormItem
import com.hermanowicz.cv.network.FormItemRequest
import com.hermanowicz.cv.network.response.FormItemResponse
import com.hermanowicz.cv.repository.UserRepository
import io.reactivex.Single
import java.util.*


class FirebaseUseCase(
    private val firebaseDB: FirebaseFirestore,
    private val repository: UserRepository
) {
    private val currentUserId = getUserId()
    private val itemList = mutableListOf<FormItem>()

    private fun getUserId(): String {
        val id = repository.userId()
        val userId = if (
            id.isNotEmpty()
        ) id else UUID.randomUUID().toString()
        if (id.isEmpty()) repository.saveUserId(userId)
        return userId
    }

    private fun FormItemResponse.toFormItemList(): FormItem {
        return FormItem(this.title, this.description, this.imageUrl, this.date)
    }

    private fun FormItem.toFormItemResponse(): FormItemResponse {
        return FormItemResponse(
            title = this.title,
            description = this.description,
            imageUrl = this.url,
            date = this.date
        )
    }

    fun getData(): Single<List<FormItem>> {
        return Single.create { publisher ->
            firebaseDB.collection(currentUserId)
                .limit(30)
                .get()
                .addOnSuccessListener {
                    val response = it.toObjects(FormItemResponse::class.java)
                    val list = response.map { item -> item.toFormItemList() }
                    itemList.addAll(list)
                    publisher.onSuccess(list)
                }
                .addOnFailureListener {
                    publisher.onError(it)
                }
        }
    }

    fun loadMore(): Single<List<FormItem>> {
        return Single.create { publisher ->
            val lastItem = itemList[itemList.size - 1]
            firebaseDB.collection(currentUserId)
                .startAfter(lastItem)
                .limit(30)
                .get()
                .addOnSuccessListener {
                    val response = it.toObjects(FormItemResponse::class.java)
                    val list = response.map { item -> item.toFormItemList() }
                    itemList.addAll(list)
                    publisher.onSuccess(list)
                }
                .addOnFailureListener {
                    publisher.onError(it)
                }
        }
    }

    fun addListener(): Single<List<FormItem>> {
        return Single.create { publisher ->
            firebaseDB.collection(currentUserId).addSnapshotListener { data, error ->
                //data?.documentChanges[0].document.toObject()
                if (error != null) publisher.onError(error)
                val response = data?.toObjects(FormItemResponse::class.java)
                val list = response?.map { item -> item.toFormItemList() } ?: listOf()
                publisher.onSuccess(list)
            }
        }
    }

    fun updateData(title: String, description: String, imageUrl: String): Single<Void> {
        val item = FormItem(title, description, imageUrl, Date())
        return Single.create { publisher ->
            firebaseDB.collection(currentUserId).document()
                .set(FormItemRequest(listOf(item.toFormItemResponse()))).addOnSuccessListener {
                    publisher.onSuccess(it)
                }.addOnFailureListener {
                    publisher.onError(it)
                }
        }
    }
}
