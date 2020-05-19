package com.hermanowicz.cv.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.hermanowicz.cv.R


fun ImageView.load(context: Context, url: String) {
    val options = RequestOptions()
        .placeholder(R.drawable.ic_launcher_foreground)

    Glide.with(context)
        .applyDefaultRequestOptions(options)
        .asDrawable()
        .centerCrop()
        .load(url)
        .into(this)
}
