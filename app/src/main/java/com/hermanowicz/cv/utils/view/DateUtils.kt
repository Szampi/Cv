package com.hermanowicz.cv.utils.view

import java.sql.Date
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*


fun Long.formatDate(): String {
    val dateFormat = SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH)
    val stamp = Timestamp(this)
    return dateFormat.format(Date(stamp.time))
}

fun currentTime(): Long {
    return System.currentTimeMillis()
}
