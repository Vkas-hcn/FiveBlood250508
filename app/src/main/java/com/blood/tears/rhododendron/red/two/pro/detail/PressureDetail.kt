package com.blood.tears.rhododendron.red.two.pro.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.blood.tears.rhododendron.red.bean.PDataBean
import com.blood.tears.rhododendron.red.databinding.ActivityPpprrBinding

class PressureDetail : AppCompatActivity() {
    private val binding by lazy { ActivityPpprrBinding.inflate(layoutInflater) }
    private var isEdit = false
    private var pressureId = ""
    private var pressureBean: PDataBean? = null
    private val presenter = PressureDetailPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupViews()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setupViews() {
        // 初始化参数
        isEdit = intent.getBooleanExtra("isEdit", false)
        pressureId = intent.getStringExtra("pressureId").orEmpty()

        if (isEdit) {
            binding.tvSave.text = "Save"
            presenter.loadExistingData(pressureId)?.let { bean ->
                pressureBean = bean
                presenter.updateUIWithBean(bean,binding)
            }
        } else {
            binding.tvSave.text = "Add"
            presenter.updateUIForNewEntry(binding)
        }

        binding.imgBack.setOnClickListener { finish() }

        binding.tvSave.setOnClickListener {
            presenter.savePressureData(this, binding, isEdit, pressureBean)
        }
        binding.vpvSys.setOnValueChangeListener { newValue ->
            presenter.updateBloodPressureState(newValue, binding.vpvDia.getSelectedValue())
                ?.let { state ->
                    presenter.updateStateUI(state,binding)
                }
        }
        binding.vpvDia.setOnValueChangeListener { newValue ->
            presenter.updateBloodPressureState(binding.vpvSys.getSelectedValue(), newValue)
                ?.let { state ->
                    presenter.updateStateUI(state,binding)
                }
        }
    }




}
