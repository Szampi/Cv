package com.hermanowicz.cv.model


data class CvData (
    val title: String,
    val subtitles: List<String>,
    var expanded: Boolean = false
)
