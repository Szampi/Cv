package com.hermanowicz.cv.screens.form

import com.hermanowicz.cv.di.common.presenter.SubscribingPresenter
import com.hermanowicz.cv.di.common.transformer.RxTransformer
import com.hermanowicz.cv.usecase.FirebaseUseCase

class FormPresenter(transformer: RxTransformer, private val firebaseUseCase: FirebaseUseCase) :
    SubscribingPresenter<FormView>(transformer) {
    fun onCreate() {
        view?.initView()
    }

    fun saveData(title: String, description: String, imageUrl: String) {
        view?.showProgressBar()
        firebaseUseCase.updateData(title, description, imageUrl).compose(transformer.single())
            .subscribe({
                view?.hideProgressBar()
                view?.finishActivity()
            }, {
                view?.hideProgressBar()
                //TODO show error
            }).remember()
    }
}
