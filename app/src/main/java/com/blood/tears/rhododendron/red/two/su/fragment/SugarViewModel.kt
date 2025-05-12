package com.blood.tears.rhododendron.red.two.su.fragment

import android.content.Intent
import androidx.fragment.app.FragmentActivity
import com.blood.tears.rhododendron.red.two.BaseViewModel
import com.blood.tears.rhododendron.red.two.su.detail.SugarDetail

class SugarViewModel:BaseViewModel() {
    fun jumpToDetail(activity:FragmentActivity,sugarId: String) {
        val intent = Intent(activity, SugarDetail::class.java)
        intent.putExtra("isEdit", true)
        intent.putExtra("sugarId", sugarId)
        activity.startActivity(intent)
    }
}