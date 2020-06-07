package com.hermanowicz.cv.network.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class FormItemResponse (
    val title: String,
    val description: String,
    val imageUrl: String,
    val date: Date
) : Parcelable
