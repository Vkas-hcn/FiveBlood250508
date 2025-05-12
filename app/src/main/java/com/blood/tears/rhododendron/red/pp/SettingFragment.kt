package com.blood.tears.rhododendron.red.pp

import android.content.Intent
import com.blood.tears.rhododendron.red.R
import com.blood.tears.rhododendron.red.databinding.ActivitysSettingBinding
import com.blood.tears.rhododendron.red.two.BaseFragment
import com.blood.tears.rhododendron.red.two.BaseViewModel

class SettingFragment :   BaseFragment<ActivitysSettingBinding, BaseViewModel>() {
    override val layoutId: Int
        get() = R.layout.activitys_setting

    override val viewModelClass: Class<BaseViewModel>
        get() = BaseViewModel::class.java
    override  fun setupViews() {
        binding.tvPP.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            //TODO 跳转网页
            intent.data =
                android.net.Uri.parse("https://www.google.com")
            startActivity(intent)
        }
    }

    override fun observeViewModel() {

    }

    override fun customizeReturnKey() {
    }

}