package com.hermanowicz.cv.screens.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.hermanowicz.cv.R
import com.hermanowicz.cv.model.CvData
import com.hermanowicz.cv.model.GithubData
import com.hermanowicz.cv.screens.main.adapters.TitleAdapter
import com.hermanowicz.cv.utils.load
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.a_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainView {
    companion object {
        const val NAME = "MainName"
        const val EMAIL = "MainEmail"
        const val IMAGE_URL = "MainImageUrl"
    }

    @Inject
    lateinit var presenter: MainPresenter

    lateinit var adapter: TitleAdapter
    lateinit var layoutManager: LinearLayoutManager
    private var imageUrl: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.a_main)
        presenter.bind(this)

        presenter.getData()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        presenter.saveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        presenter.restoreState(savedInstanceState)
    }

    override fun initView(data: GithubData) {
        layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapter = TitleAdapter()
        titleRecycler.adapter = adapter
        titleRecycler.layoutManager = layoutManager
        titleRecycler.setHasFixedSize(true)

        image.load(this, data.image)
        name.text = data.fullName
        email.text = data.email
        imageUrl = data.image
    }

    override fun displayCv(data: List<CvData>) {
        adapter.addItems(data)
    }

    override fun saveDataForState(outState: Bundle) {
        outState.putCharSequence(NAME, name.text)
        outState.putCharSequence(EMAIL, email.text)
        outState.putString(IMAGE_URL, imageUrl)
    }

    override fun displayDataFromState(savedInstanceState: Bundle?) {
        savedInstanceState?.let {
            name.text = it.getCharSequence(NAME)
            email.text = it.getCharSequence(EMAIL)
            val url = it.getString(IMAGE_URL)
            url?.let { imageUrl -> image.load(this, imageUrl) }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unbind()
        presenter.onDestroy()
    }
}
