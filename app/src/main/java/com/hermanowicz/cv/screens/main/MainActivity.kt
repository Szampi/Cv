package com.hermanowicz.cv.screens.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.hermanowicz.cv.R
import com.hermanowicz.cv.model.FormItem
import com.hermanowicz.cv.screens.form.FormActivity
import com.hermanowicz.cv.screens.main.adapters.FormItemAdapter
import com.hermanowicz.cv.screens.main.adapters.FormItemClickedListener
import com.hermanowicz.cv.utils.view.start
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.a_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainView, FormItemClickedListener {

    @Inject
    lateinit var presenter: MainPresenter

    lateinit var adapter: FormItemAdapter
    lateinit var layoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.a_main)
        presenter.bind(this)
        presenter.getList()
    }

    override fun initView() {
        layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapter = FormItemAdapter(this)
        formItemRecycler.adapter = adapter
        formItemRecycler.layoutManager = layoutManager

        fab.setOnClickListener {
            presenter.onFabClicked()
        }
    }

    override fun displayToDoList(data: List<FormItem>) {
        adapter.addItems(data)
    }

    override fun showForm(item: FormItem, documentId: String) {
        val bundle = FormActivity.createExtras(documentId, item)
        start<FormActivity>(bundle)
    }

    override fun showForm() {
        start<FormActivity>()
    }

    override fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unbind()
        presenter.onDestroy()
    }

    override fun onFormItemClicked(item: FormItem, position: Int) {
        presenter.getDocument(item, position)
    }

    override fun onResume() {
        super.onResume()
        presenter.collectionListener()
    }
}
