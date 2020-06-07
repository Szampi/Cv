package com.hermanowicz.cv.utils.view

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.hermanowicz.cv.R


fun ImageView.load(context: Context, url: String) {
    val options = RequestOptions()
        .placeholder(R.drawable.ic_launcher_foreground)
        .transform(CircleCrop())

    Glide.with(context)
        .applyDefaultRequestOptions(options)
        .asDrawable()
        .load(url)
        .into(this)
}
