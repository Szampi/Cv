package com.hermanowicz.cv.network

import android.os.Parcelable
import com.hermanowicz.cv.model.FormItem
import com.hermanowicz.cv.network.response.FormItemResponse
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FormItemRequest(
    val list: List<FormItemResponse>
) : Parcelable
