package com.hermanowicz.cv.utils.view

import android.widget.EditText
import androidx.core.widget.addTextChangedListener

fun EditText.showError(limit: Int, message: String) {
    this.addTextChangedListener { text ->
        text?.let {
            if (it.length >= limit) this.error = message
        }
    }
}
