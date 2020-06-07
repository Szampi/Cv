package com.hermanowicz.cv.utils.view

import androidx.appcompat.app.AppCompatActivity


inline fun <reified Activity : AppCompatActivity> AppCompatActivity.start() {
    val intent = android.content.Intent(this, Activity::class.java)
    startActivity(intent)
}

inline fun <reified Activity : AppCompatActivity> AppCompatActivity.start(extras: android.os.Bundle) {
    val intent = android.content.Intent(this, Activity::class.java)
    intent.putExtras(extras)
    startActivity(intent)
}

@android.annotation.TargetApi(android.os.Build.VERSION_CODES.M)
fun AppCompatActivity.checkPermission(permission: String, requestCode: Int, permissionGrantedCallback: (() -> Unit)?) {
    if (hasPermissions(permission))
        permissionGrantedCallback?.invoke()
    else
        requestPermissions(arrayOf(permission), requestCode)
}
