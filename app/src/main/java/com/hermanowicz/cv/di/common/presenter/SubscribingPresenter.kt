package com.hermanowicz.cv.di.common.presenter

import com.hermanowicz.cv.di.common.transformer.RxTransformer
import com.hermanowicz.cv.di.common.view.View
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class SubscribingPresenter<SubscribingView : View>(protected val transformer: RxTransformer) :
    Presenter<SubscribingView>() {

    protected val subscriptions = CompositeDisposable()

    open fun onDestroy() {
        subscriptions.clear()
    }

    protected fun Disposable.remember() {
        subscriptions.add(this)
    }

}
