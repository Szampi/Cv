package com.hermanowicz.cv.utils.view

import com.google.firebase.firestore.DocumentSnapshot
import com.hermanowicz.cv.model.FormItem
import com.hermanowicz.cv.network.response.FormItemResponse


fun FormItemResponse?.formItem(): FormItem {
    if (this == null) return FormItem(
        "Empty",
        "Empty",
        "https://i.picsum.photos/id/20/60/60.jpg",
        currentTime()
    )
    return FormItem(this.title, this.description, this.imageUrl, this.date)
}

fun FormItem.toFormItemResponse(): FormItemResponse {
    return FormItemResponse(
        title = this.title,
        description = this.description,
        imageUrl = this.url,
        date = this.date
    )
}

fun DocumentSnapshot?.formItemResponse(): FormItemResponse {
    val title = this?.get("title") as? String ?: ""
    val imageUrl = this?.get("imageUrl") as? String ?: ""
    val description = this?.get("description") as? String ?: ""
    val date = this?.get("date") as? Long ?: 0L
    return FormItemResponse(
        title = title,
        description = description,
        imageUrl = imageUrl,
        date = date
    )
}
