package com.hermanowicz.cv.screens.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hermanowicz.cv.R
import com.hermanowicz.cv.model.GithubData
import com.hermanowicz.cv.utils.load
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.a_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainView {

    @Inject
    lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.a_main)
        presenter.bind(this)

        presenter.getData()
    }

    override fun displayData(data: GithubData) {
        image.load(this, data.image)
        name.text = data.fullName
        email.text = data.email
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unbind()
        presenter.onDestroy()
    }
}
