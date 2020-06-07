package com.hermanowicz.cv.screens.form

import com.hermanowicz.cv.di.common.presenter.SubscribingPresenter
import com.hermanowicz.cv.di.common.transformer.RxTransformer

class FormPresenter(transformer: RxTransformer) : SubscribingPresenter<FormView>(transformer) {
    fun onCreate() {
        view?.initView()
    }

}
