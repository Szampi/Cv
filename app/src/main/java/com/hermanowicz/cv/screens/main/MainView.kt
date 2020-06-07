package com.hermanowicz.cv.screens.main

import com.hermanowicz.cv.di.common.view.View
import com.hermanowicz.cv.model.FormItem
import com.hermanowicz.cv.model.GithubData

interface MainView : View {
    fun initView()
    fun displayToDoList(data: List<FormItem>)
    fun showForm(item: FormItem, documentId: String)
    fun showForm()
    fun showProgressBar()
    fun hideProgressBar()
}
