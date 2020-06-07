package com.hermanowicz.cv.screens.form

import android.app.Activity
import android.os.Bundle
import com.hermanowicz.cv.R
import com.hermanowicz.cv.model.FormItem
import com.hermanowicz.cv.utils.showError
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.a_form.*
import javax.inject.Inject


class FormActivity : Activity(), FormView {

    companion object {
        const val TITLE = "TITLE"
        const val DESCRIPTION = "DESCRIPTION"
        const val URL = "URL"
        const val FORM_ITEM = "FORM_ITEM"

        fun createExtras(item: FormItem): Bundle {
            return Bundle().apply {
                putParcelable(FORM_ITEM, item)
            }
        }
    }

    @Inject
    lateinit var presenter: FormPresenter
    private val formItem by lazy { intent.extras?.getParcelable<FormItem>(FORM_ITEM) }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.a_form)
        presenter.bind(this)
        presenter.onCreate()
    }

    override fun setupView() {
        formItem?.let {
            descriptionField.setText(it.description)
            titleField.setText(it.title)
            urlField.setText(it.url)
        }

        titleField.showError(30, getString(R.string.titleError))
        descriptionField.showError(200, getString(R.string.descriptionError))
    }
}
