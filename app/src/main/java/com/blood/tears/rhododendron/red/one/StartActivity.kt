package com.blood.tears.rhododendron.red.one

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Bundle
import android.content.Intent
import android.view.animation.LinearInterpolator
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.blood.tears.rhododendron.red.R
import com.blood.tears.rhododendron.red.databinding.ActivityMainBinding
import com.blood.tears.rhododendron.red.databinding.ActivityStartBinding
import com.blood.tears.rhododendron.red.two.BaseActivity
import com.blood.tears.rhododendron.red.two.BaseViewModel
import com.blood.tears.rhododendron.red.two.main.MainActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class StartActivity  : BaseActivity<ActivityStartBinding, BaseViewModel>() {
    override val layoutId: Int
        get() = R.layout.activity_start
    override val viewModelClass: Class<BaseViewModel>
        get() = BaseViewModel::class.java



    override fun setupViews() {
        onBackPressedDispatcher.addCallback {
        }
        startInfiniteRotation()
        lifecycleScope.launch {
            delay(2000L)
            startActivity(Intent(this@StartActivity, MainActivity::class.java))
            finish()
        }    }

    override fun observeViewModel() {
    }

    private fun startInfiniteRotation() {
        val animator = ObjectAnimator.ofFloat(binding.imgLoad, "rotation", 0f, 360f).apply {
            duration = 800 // 动画持续时间（毫秒）
            repeatCount = ValueAnimator.INFINITE // 无限重复
            repeatMode = ValueAnimator.RESTART // 无缝衔接开始和结束
            interpolator = LinearInterpolator() // 匀速旋转
        }
        animator.start()
    }
}