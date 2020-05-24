package com.hermanowicz.cv.screens.main

import android.os.Bundle
import com.hermanowicz.cv.di.common.view.View
import com.hermanowicz.cv.model.CvData
import com.hermanowicz.cv.model.GithubData

interface MainView : View {
    fun initView(data: GithubData)
    fun displayCv(data: List<CvData>)
    fun saveDataForState(outState: Bundle)
    fun displayDataFromState(savedInstanceState: Bundle?)
}
