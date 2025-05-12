package com.blood.tears.rhododendron.red.two.main

import android.os.Bundle
import androidx.activity.addCallback
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.blood.tears.rhododendron.red.R
import com.blood.tears.rhododendron.red.databinding.ActivityMainBinding
import com.blood.tears.rhododendron.red.two.BaseActivity
import com.blood.tears.rhododendron.red.two.BaseViewModel

class MainActivity : BaseActivity<ActivityMainBinding, BaseViewModel>() {
    override val layoutId: Int
        get() = R.layout.activity_main

    override val viewModelClass: Class<BaseViewModel>
        get() = BaseViewModel::class.java

    override fun setupViews() {
        val navView: BottomNavigationView = binding.navView
        navView.itemIconTintList  = null
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        navView.setupWithNavController(navController)
        onBackPressedDispatcher.addCallback {
            finish()
        }
    }

    override fun observeViewModel() {
    }
    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }
}

