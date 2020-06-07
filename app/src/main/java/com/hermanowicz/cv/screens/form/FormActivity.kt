package com.hermanowicz.cv.screens.form

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.hermanowicz.cv.R
import com.hermanowicz.cv.model.FormItem
import com.hermanowicz.cv.utils.dialog.showErrorDialog
import com.hermanowicz.cv.utils.view.showError
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.a_form.*
import javax.inject.Inject


class FormActivity : AppCompatActivity(), FormView {

    companion object {
        const val FORM_ITEM = "FORM_ITEM"
        const val DOCUMENT_ID = "DOCUMENT_ID"

        fun createExtras(documentId: String, item: FormItem): Bundle {
            return Bundle().apply {
                putParcelable(FORM_ITEM, item)
                putString(DOCUMENT_ID, documentId)
            }
        }
    }

    @Inject
    lateinit var presenter: FormPresenter
    private val formItem by lazy { intent.extras?.getParcelable<FormItem>(FORM_ITEM) }
    private val documentId by lazy { intent.extras?.getString(DOCUMENT_ID) ?: "" }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.a_form)
        presenter.bind(this)
        presenter.onCreate()
    }

    override fun initView() {
        formItem?.let {
            titleField.setText(it.title)
            descriptionField.setText(it.description)
            urlField.setText(it.url)
        }

        titleField.showError(30, getString(R.string.titleError))
        descriptionField.showError(200, getString(R.string.descriptionError))

        save.setOnClickListener {
            presenter.saveData(
                documentId = documentId,
                title = titleField.text.toString(),
                description = descriptionField.text.toString(),
                imageUrl = urlField.text.toString()
            )
        }
    }

    override fun finishActivity() {
        finish()
    }

    override fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }

    override fun showError(message: String?) {
        showErrorDialog(this, message)
    }
}
