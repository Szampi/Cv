package com.hermanowicz.cv.screens.main

import com.hermanowicz.cv.di.common.presenter.SubscribingPresenter
import com.hermanowicz.cv.di.common.transformer.RxTransformer
import com.hermanowicz.cv.model.FormItem
import com.hermanowicz.cv.usecase.FirebaseUseCase

class MainPresenter(
    transformer: RxTransformer, private val firebaseUseCase: FirebaseUseCase
) :
    SubscribingPresenter<MainView>(transformer) {
    private val list = mutableListOf<FormItem>()

    fun getList() {
        view?.initView()
        view?.showProgressBar()
        firebaseUseCase.getData().compose(transformer.single()).subscribe({
            view?.displayToDoList(it)
            list.addAll(it)
        }, {
            view?.hideProgressBar()
            //TODO show error
        }).remember()


    }

    fun collectionListener() {
        firebaseUseCase.addListener().compose(transformer.single()).subscribe({
            view?.displayToDoList(it)
            view?.hideProgressBar()
            list.clear()
            list.addAll(it)
        }, {
            view?.hideProgressBar()
            //TODO show error
        }).remember()
    }

    fun loadMoreItems() {
        view?.showProgressBar()
        firebaseUseCase.loadMore().compose(transformer.single()).subscribe({
            view?.displayToDoList(it)
            view?.hideProgressBar()
            list.addAll(it)
        }, {
            view?.hideProgressBar()
            //TODO show error prompt
        }).remember()
    }

    fun onFabClicked() {
        view?.showForm()
    }

    fun getDocument(item: FormItem, position: Int) {
        val documentId = firebaseUseCase.documentId(position)
        view?.showForm(item, documentId)
    }
}
