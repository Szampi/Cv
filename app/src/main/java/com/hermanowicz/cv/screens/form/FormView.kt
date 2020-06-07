package com.hermanowicz.cv.screens.form

import com.hermanowicz.cv.di.common.view.View

interface FormView: View {
    fun initView()
    fun finishActivity()
    fun showProgressBar()
    fun hideProgressBar()
    fun showError(message: String?)
}
