package com.hermanowicz.cv.usecase

import com.google.firebase.firestore.FirebaseFirestore
import com.hermanowicz.cv.model.FormItem
import com.hermanowicz.cv.repository.UserRepository
import com.hermanowicz.cv.utils.view.currentTime
import com.hermanowicz.cv.utils.view.formItem
import com.hermanowicz.cv.utils.view.formItemResponse
import io.reactivex.Single
import java.util.*

class FirebaseUseCase(
    private val firebaseDB: FirebaseFirestore,
    private val repository: UserRepository
) {
    private val currentUserId = getUserId()
    private val itemList = mutableListOf<FormItem>()
    private val documentIds = mutableListOf<String>()

    private fun getUserId(): String {
        val id = repository.userId()
        val userId = if (
            id.isNotEmpty()
        ) id else UUID.randomUUID().toString()
        if (id.isEmpty()) repository.saveUserId(userId)
        return userId
    }

    fun getData(): Single<List<FormItem>> {
        return Single.create { publisher ->
            firebaseDB.collection(currentUserId)
                .limit(30)
                .get()
                .addOnSuccessListener {
                    val todoList = it.documents.map { doc ->
                        doc.formItemResponse().formItem()
                    }
                    documentIds.addAll(it.documents.map { doc -> doc.id })
                    itemList.addAll(todoList)
                    publisher.onSuccess(todoList)
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
                    val todoList = it.documents.map { doc ->
                        doc.id
                        doc.formItemResponse().formItem()
                    }
                    itemList.addAll(todoList)
                    publisher.onSuccess(todoList)
                }
                .addOnFailureListener {
                    publisher.onError(it)
                }
        }
    }

    fun addListener(): Single<List<FormItem>> {
        return Single.create { publisher ->
            firebaseDB.collection(currentUserId).addSnapshotListener { data, error ->
                if (error != null) publisher.onError(error)
                val todoList = data?.documents?.map { doc ->
                    doc.formItemResponse().formItem()
                } ?: listOf()
                publisher.onSuccess(todoList)
            }
        }
    }

    fun updateData(
        documentId: String,
        title: String,
        description: String,
        imageUrl: String
    ): Single<Boolean> {
        val item = mapOf(
            "title" to title,
            "description" to description,
            "imageUrl" to imageUrl,
            "date" to currentTime()
        )
        return Single.create { publisher ->
            if (documentId.isEmpty()) {
                firebaseDB.collection(currentUserId).document().set(item)
                    .addOnSuccessListener { publisher.onSuccess(true) }
                    .addOnFailureListener { publisher.onError(it) }
            } else {
                firebaseDB.collection(currentUserId).document(documentId).update(item)
                    .addOnSuccessListener {
                        publisher.onSuccess(true)
                    }.addOnFailureListener {
                        publisher.onError(it)
                    }
            }
        }
    }

    fun documentId(position: Int): String {
        return documentIds[position]
    }
}
