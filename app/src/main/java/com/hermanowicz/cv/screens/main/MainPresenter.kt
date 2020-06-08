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
            view?.hideProgressBar()
            view?.displayToDoList(it)
            list.addAll(it)
        }, {
            view?.hideProgressBar()
            view?.showError(it.message)
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
            view?.showError(it.message)
        }).remember()
    }

    fun loadMoreItems() {
        firebaseUseCase.loadMore().compose(transformer.single()).subscribe({
            if (it.isNotEmpty() && !list.containsAll(it)) {
                list.addAll(it)
                view?.displayToDoList(list)
            }
        }, {
            view?.showError(it.message)
        }).remember()
    }

    fun onFabClicked() {
        view?.showForm()
    }

    fun getDocument(item: FormItem, position: Int) {
        val documentId = firebaseUseCase.documentId(position)
        view?.showForm(item, documentId)
    }

    fun removeItem(adapterPosition: Int) {
        firebaseUseCase.removeItem(adapterPosition).subscribe({
            view?.updateRemovedItem(adapterPosition)
        }, {
            view?.showError(it.message)
        }).remember()
    }

    fun openConfirmationPrompt(position: Int) {
        view?.showConfirmationPrompt(position)
    }
}
