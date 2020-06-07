package com.hermanowicz.cv.screens.main

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.hermanowicz.cv.di.common.presenter.SubscribingPresenter
import com.hermanowicz.cv.di.common.transformer.RxTransformer
import com.hermanowicz.cv.model.FormItem
import com.hermanowicz.cv.network.FormItemRequest
import com.hermanowicz.cv.network.response.FormItemResponse
import com.hermanowicz.cv.usecase.GithubUseCase
import java.util.*

class MainPresenter(
    transformer: RxTransformer, private val githubUseCase: GithubUseCase,
    private val firebaseDB: FirebaseFirestore
) :
    SubscribingPresenter<MainView>(transformer) {


    fun getFirebaseData() {
        firebaseDB.firestoreSettings = FirebaseFirestoreSettings.Builder().build()

        firebaseDB.collection("toDoList").document("todo").get().addOnSuccessListener {

            Log.d("FIREBASE ::: :: ", "$it")
//            val formItemResponse = it.toObject(FormItemRequest::class.java) ?: FormItemRequest(listOf())
////                "Empty",
////                "Empty",
////                "",
////                Date()
////            )
//            Log.d("SUCCESS firebase:: ", "$formItemResponse")
        }.addOnFailureListener {
            Log.d("ERROR FIREBASE:: ", "${it.message}")
        }
    }

    fun editData() {
        val item = FormItemResponse(
            title = "two",
            description = "s",
            imageUrl = "https://i.picsum.photos/id/150/60/60.jpg",
            date = Date()
        )
        val item2 = FormItemResponse(
            title = "three",
            description = "a",
            imageUrl = "https://i.picsum.photos/id/122/60/60.jpg",
            date = Date()
        )
        firebaseDB.collection("toDoList").document("user123123123")
            .set(FormItemRequest(listOf(item, item2))).addOnSuccessListener {
                Log.d("SUCCESS firebase:: ", "")
            }.addOnFailureListener {
                Log.d("ERROR :: ", "${it.message}")
            }
    }

    fun getCvData() {
        view?.initView()
        view?.displayToDoList(
            data = listOf(
                FormItem(
                    "one",
                    "v",
                    "https://i.picsum.photos/id/140/60/60.jpg",
                    Date()
                ),
                FormItem(
                    "two",
                    "ert ert ert",
                    "https://i.picsum.photos/id/230/60/60.jpg",
                    Date()
                ),
                FormItem(
                    "thee",
                    "asad as das dc asad as das dc asad as das dc asad as das dc asad as das dc asad as das dc asad as das dc asad as das dc asad as das dc asad as das dc asad as das dc asad as das dc asad as das dc ",
                    "https://i.picsum.photos/id/210/60/60.jpg",
                    Date()
                )
            )
        )
    }

    fun onFabClicked() {
        view?.showForm()
    }

}
