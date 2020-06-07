package com.hermanowicz.cv.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FormItem(
    val title: String,
    val description: String,
    val url: String,
    val date: Long
) : Parcelable
