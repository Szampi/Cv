package com.hermanowicz.cv.screens.form

import android.os.Bundle
import com.hermanowicz.cv.di.common.view.View

interface FormView: View {
    fun initView()
    fun finishActivity()
    fun showProgressBar()
    fun hideProgressBar()
    fun showError(message: String?)
    fun saveState(outState: Bundle)
    fun showSavedState(savedInstanceState: Bundle)
}
