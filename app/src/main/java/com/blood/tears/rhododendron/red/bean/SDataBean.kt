package com.blood.tears.rhododendron.red.bean

import androidx.annotation.Keep

@Keep
data class SDataBean(
    val numL: Double,
    val numDL: Double,
    val state: AllUtils.BloodSugarStatus,
    val currentState: AllUtils.CurrentState,
    val date: String,
    val id:String,
)