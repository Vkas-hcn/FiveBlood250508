package com.blood.tears.rhododendron.red.two.su.detail

import android.widget.Toast
import androidx.core.view.isVisible
import com.blood.tears.rhododendron.red.R
import com.blood.tears.rhododendron.red.bean.AllUtils
import com.blood.tears.rhododendron.red.bean.AllUtils.closeKeyboard
import com.blood.tears.rhododendron.red.bean.AllUtils.saveSugar
import com.blood.tears.rhododendron.red.bean.AllUtils.updateSugar
import com.blood.tears.rhododendron.red.bean.AppUtils
import com.blood.tears.rhododendron.red.bean.SDataBean
import com.blood.tears.rhododendron.red.databinding.ActivitySsssdBinding
import com.blood.tears.rhododendron.red.two.BaseActivity

class SugarDetail : BaseActivity<ActivitySsssdBinding, SugarDetailViewModel>() {

    private var isEdit = false
    private var sugarId = ""
    private var sugarBean: SDataBean? = null
    private var currentStateSugar = AllUtils.NORMAL
    override val layoutId: Int
        get() = R.layout.activity_ssssd

    override val viewModelClass: Class<SugarDetailViewModel>
        get() = SugarDetailViewModel::class.java

    override fun setupViews() {
        // 初始化参数
        isEdit = intent.getBooleanExtra("isEdit", false)
        sugarId = intent.getStringExtra("sugarId").orEmpty()
        if (isEdit) {
            viewModel.loadExistingData(sugarId)?.let { bean ->
                sugarBean = bean
                viewModel.setSugarUnit()
                updateUIWithBean(bean)
            }
        } else {
            updateUIForNewEntry()
        }

        // 返回按钮
        binding.imgBack.setOnClickListener { finish() }

        // 单位切换按钮
        binding.tvSugarUnit.setOnClickListener {
            toggleSugarUnit()
        }

        // 保存按钮
        binding.tvSave.setOnClickListener {
            saveSugarData()
        }

        // 显示对话框按钮
        binding.flBefore.setOnClickListener {
            binding.conDialog.isVisible = true
            this.closeKeyboard()
        }

        // 取消对话框按钮
        binding.imgCancel.setOnClickListener {
            binding.conDialog.isVisible = false
        }

        // 当前状态按钮
        binding.tvSugarStateNormal.setOnClickListener { clickCurrentState(AllUtils.NORMAL) }
        binding.tvSugarStateBeforeMeals.setOnClickListener { clickCurrentState(AllUtils.BEFORE_MEAL) }
        binding.tvSugarStateBeforeExercise.setOnClickListener { clickCurrentState(AllUtils.BEFORE_EXERCISE) }
        binding.tvSugarStateAfterExercise.setOnClickListener { clickCurrentState(AllUtils.AFTER_EXERCISE) }
        binding.tvSugarState1hour.setOnClickListener { clickCurrentState(AllUtils.ONE_HOUR_AFTER_MEAL) }
        binding.tvSugarStateSleep.setOnClickListener { clickCurrentState(AllUtils.ASLEEP) }
        binding.tvSugarState2hours.setOnClickListener { clickCurrentState(AllUtils.TWO_HOURS_AFTER_MEAL) }
        binding.tvSugarStateBeforeFasting.setOnClickListener { clickCurrentState(AllUtils.FASTING) }

        // 输入框监听
        setupInputListeners()
    }

    override fun observeViewModel() {
    }


    private fun setupInputListeners() {
        binding.edSugarNum.addTextChangedListener(object : android.text.TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: android.text.Editable?) {
                s?.let {
                    if (it.isNotBlank()) {
                        val num = it.toString().toDouble()
                        val state = viewModel.updateBloodSugarStatus(currentStateSugar, num)
                        updateStateUI(state)
                    }
                }
            }
        })
    }

    private fun toggleSugarUnit() {
        val currentUnit = viewModel.toggleSugarUnit()
        binding.tvSugarUnit.text = currentUnit
        val numText = binding.edSugarNum.text.toString().trim()
        if (numText.isNotBlank()) {
            val num = numText.toDouble()
            val convertedNum = viewModel.convertSugarNum(num)
            binding.edSugarNum.setText(String.format("%.2f", convertedNum))
            val state = viewModel.updateBloodSugarStatus(currentStateSugar, convertedNum)
            updateStateUI(state)
        }
    }

    private fun saveSugarData() {
        val numText = binding.edSugarNum.text.toString().trim()
        if (numText.isBlank()) {
            Toast.makeText(this, "Please enter the amount of sugar", Toast.LENGTH_SHORT).show()
            return
        }

        val num = numText.toDouble()
        if (num <= 0) {
            Toast.makeText(
                this,
                "The amount of sugar cannot be less than or equal to 0",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        val newBean = viewModel.createSugarBean(
            numL = viewModel.getSugarNumToL(num),
            numDL = viewModel.getSugarNumToDl(num),
            date = AppUtils.getDateTime(System.currentTimeMillis().toString()),
            currentState = currentStateSugar,
            id = if (isEdit) sugarBean?.id ?: "" else System.currentTimeMillis().toString()
        )

        if (isEdit) {
            newBean.updateSugar()
        } else {
            newBean.saveSugar()
        }

        finish()
    }

    private fun clickCurrentState(currentState: String) {
        currentStateSugar = currentState
        binding.sugarCurrentState = currentState.toString()
        val numText = binding.edSugarNum.text.toString().trim()
        if (numText.isNotBlank()) {
            val num = numText.toDouble()
            val state = viewModel.updateBloodSugarStatus(currentState, num)
            updateStateUI(state)
        }
        updateUIForCurrentState()
    }

    private fun updateStateUI(state: String) {
        binding.colorState = state
        binding.tvDetailState.text = state
        binding.tvDetailState.setTextColor(AppUtils.getSugarStateColorString(state))
        binding.imgCc.setImageResource(AppUtils.getSugarStateImage(null, state))
        setIntState()
    }

    private fun updateUIWithBean(bean: SDataBean) {
        binding.sugarUnit = viewModel.getCurrentSugarUnit()
        binding.tvSugarUnit.text = viewModel.getCurrentSugarUnitText()
        binding.sugarCurrentState = bean.currentState.toString()
        currentStateSugar = bean.currentState
        binding.edSugarNum.setText(viewModel.getSugarNumText(bean).toString())
        binding.tvDetailDate.text = "Datetime:${bean.date}"
        val state = viewModel.updateBloodSugarStatus(bean.currentState, bean.numL)
        updateStateUI(state)
    }

    private fun updateUIForNewEntry() {
        binding.sugarUnit = AllUtils.L
        binding.sugarCurrentState = AllUtils.NORMAL.toString()
        binding.tvDetailDate.text =
            "Datetime:${AppUtils.getDateTime(System.currentTimeMillis().toString())}"
        val state = viewModel.updateBloodSugarStatus(currentStateSugar, 0.0)
        updateStateUI(state)
    }

    private fun updateUIForCurrentState() {
        binding.tvDetailState.text = binding?.colorState!!
        binding.tvDetailState.setTextColor(AppUtils.getSugarStateColorString(binding?.colorState!!))
        binding.imgCc.setImageResource(AppUtils.getSugarStateImage(null, binding?.colorState!!))
        setIntState()
    }

    private fun setIntState() {
        binding.colorInt = viewModel.getColorInt(binding?.colorState!!)
        binding.sugarCurrentInt = viewModel.getCurrentStateInt(binding?.sugarCurrentState!!)
    }
}
