package com.hermanowicz.cv.di.common.presenter

import com.hermanowicz.cv.di.common.view.View

abstract class Presenter<T : View> {

    protected var view: T? = null

    open fun bind(view: T) {
        this.view = view
    }

    open fun unbind() {
        view = null
    }

}
