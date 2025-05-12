package com.blood.tears.rhododendron.red.two.pro.fragment

import android.content.Intent
import androidx.fragment.app.FragmentActivity
import com.blood.tears.rhododendron.red.two.BaseViewModel
import com.blood.tears.rhododendron.red.two.pro.detail.PressureDetail

class PressureViewModel:BaseViewModel() {
    fun jumpToDetail(activity:FragmentActivity,pressureId: String) {
        val intent = Intent(activity, PressureDetail::class.java)
        intent.putExtra("isEdit", true)
        intent.putExtra("pressureId", pressureId)
        activity.startActivity(intent)
    }
}