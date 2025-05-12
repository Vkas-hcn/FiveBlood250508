package com.blood.tears.rhododendron.red.two.pro.detail


import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.blood.tears.rhododendron.red.bean.AllUtils
import com.blood.tears.rhododendron.red.bean.AppUtils.checkBloodPressure
import com.blood.tears.rhododendron.red.bean.AppUtils.getBloodPressureState
import com.blood.tears.rhododendron.red.bean.AppUtils.getBloodPressureStateColor
import com.blood.tears.rhododendron.red.bean.AppUtils.getBloodPressureStateImage
import com.blood.tears.rhododendron.red.bean.AppUtils.getDateTime
import com.blood.tears.rhododendron.red.bean.PDataBean
import com.blood.tears.rhododendron.red.databinding.ActivityPDBinding

class PressureDetailPresenter {

    fun loadExistingData(pressureId: String): PDataBean? {
        return AllUtils.getPressureListData().find { it.id == pressureId }
    }

    fun createPressureBean(
        systolic: Int,
        diatonic: Int,
        pultonic: Int,
        date: String,
        id: String
    ): PDataBean {
        return PDataBean(
            systolic = systolic,
            diatonic = diatonic,
            pultonic = pultonic,
            date = date,
            id = id
        )
    }

    fun updateBloodPressureState(sys: Int, dia: Int): Int? {
        return checkBloodPressure(sys, dia)
    }
     fun savePressureData(activity: AppCompatActivity,binding: ActivityPDBinding, isEdit: Boolean, pressureBean: PDataBean?) {

        val sys = binding.vpvSys.getSelectedValue()
        val dia = binding.vpvDia.getSelectedValue()
        val pul = binding.vpvPulse.getSelectedValue()

        if (sys <= 0 || dia  <= 0 || pul <= 0) {
            Toast.makeText(activity, "Please enter a value greater than 0", Toast.LENGTH_SHORT).show()
            return
        }

        val newBean = createPressureBean(
            systolic = sys,
            diatonic = dia,
            pultonic = pul,
            date = getDateTime(System.currentTimeMillis().toString()),
            id = if (isEdit) pressureBean?.id ?: "" else System.currentTimeMillis().toString()
        )

        if (isEdit) {
            AllUtils.updatePressure(newBean)
        } else {
            AllUtils.savePressure(newBean)
        }

        activity.finish()
    }

     fun updateStateUI(state: Int,binding: ActivityPDBinding) {
        binding.tvDetailState.text = getBloodPressureState(state)
        binding.tvDetailState.setTextColor(getBloodPressureStateColor(state))
        binding.imgCc.setImageResource(getBloodPressureStateImage(state))
        val sys = binding.vpvSys.getSelectedValue()
        val dia = binding.vpvDia.getSelectedValue()

        try {
            binding.colorState = checkBloodPressure(sys, dia)
        } catch (e: Exception) {
            binding.colorState = 2
        }
    }

     fun updateUIWithBean(bean: PDataBean,binding: ActivityPDBinding) {
        binding.vpvSys.setDefaultValue(bean.systolic)
        binding.vpvDia.setDefaultValue(bean.diatonic)
        binding.vpvPulse.setDefaultValue(bean.pultonic)
        binding.colorState = checkBloodPressure(bean.systolic, bean.diatonic)
        binding.tvDetailDate.text = bean.date
        binding.atvDate.text = "Datetime:${bean.date}"
        updateStateUI(binding.colorState ?: 2,binding)
    }

     fun updateUIForNewEntry(binding: ActivityPDBinding) {
        binding.tvDetailDate.text = getDateTime(System.currentTimeMillis().toString())
        binding.atvDate.text =
            "Datetime:${getDateTime(System.currentTimeMillis().toString())}"
        binding.colorState = 2
        binding.vpvSys.setDefaultValue(100)
        binding.vpvDia.setDefaultValue(70)
        binding.vpvPulse.setDefaultValue(70)
        updateStateUI(2,binding)
    }

}
