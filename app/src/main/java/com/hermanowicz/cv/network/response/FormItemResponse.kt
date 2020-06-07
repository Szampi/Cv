package com.hermanowicz.cv.network.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FormItemResponse (
    val title: String,
    val description: String,
    val imageUrl: String,
    val date: Long
) : Parcelable
