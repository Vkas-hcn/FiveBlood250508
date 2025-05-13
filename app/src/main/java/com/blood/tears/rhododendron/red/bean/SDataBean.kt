package com.blood.tears.rhododendron.red.bean

import androidx.annotation.Keep

@Keep
data class SDataBean(
    val numL: Double,
    val numDL: Double,
    val state: String,
    val currentState: String,
    val date: String,
    val id:String,
)