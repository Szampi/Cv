package com.hermanowicz.cv.di.common.transformer

import io.reactivex.Scheduler
import io.reactivex.SingleTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class RxTransformer {

    private val main: Scheduler
        get() = AndroidSchedulers.mainThread()

    private val ioScheduler: Scheduler
        get() = Schedulers.io()

    fun <T> single(subscribing: Scheduler = ioScheduler, observing: Scheduler = main) = SingleTransformer<T, T> {
        it.subscribeOn(subscribing).observeOn(observing)
    }
}
