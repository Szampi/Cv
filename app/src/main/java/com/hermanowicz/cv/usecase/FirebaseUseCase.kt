package com.hermanowicz.cv.usecase

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.hermanowicz.cv.model.FormItem
import com.hermanowicz.cv.repository.UserRepository
import com.hermanowicz.cv.utils.view.currentTime
import com.hermanowicz.cv.utils.view.formItem
import com.hermanowicz.cv.utils.view.formItemResponse
import io.reactivex.Single

class FirebaseUseCase(
    private val firebaseDB: FirebaseFirestore,
    private val repository: UserRepository
) {
    private val currentUserId = getUserId()
    private val itemList = mutableListOf<FormItem>()
    private val documentIds = mutableListOf<String>()
    private val snapshotsList = mutableListOf<DocumentSnapshot>()

    private fun getUserId(): String {
        val id = repository.userId()
        repository.saveUserId(id)
        return repository.userId()
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
                    snapshotsList.addAll(it.documents)
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
            val lastItem = snapshotsList[snapshotsList.size - 1]
            firebaseDB.collection(currentUserId)
                .startAfter(lastItem)
                .limit(30)
                .get()
                .addOnSuccessListener {
                    val todoList = it.documents.map { doc ->
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
                documentIds.clear()
                snapshotsList.addAll(data?.documents ?: listOf())
                documentIds.addAll(data?.documents?.map { doc -> doc.id } ?: listOf())

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

    fun removeItem(adapterPosition: Int): Single<Boolean> {
        return Single.create { publisher ->
            firebaseDB.collection(currentUserId).document(documentId(adapterPosition)).delete()
                .addOnSuccessListener {
                    publisher.onSuccess(true)
                }.addOnFailureListener { error ->
                    publisher.onError(error)
                }
        }
    }
}
