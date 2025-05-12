package com.blood.tears.rhododendron.red.bean

import androidx.annotation.Keep

@Keep
data class PDataBean(
    val systolic: Int,
    val diatonic: Int,
    val pultonic:Int,
    val date: String,
    val id: String
)