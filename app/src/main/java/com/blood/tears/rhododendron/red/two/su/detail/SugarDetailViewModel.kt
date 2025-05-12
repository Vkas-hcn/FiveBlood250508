package com.blood.tears.rhododendron.red.two.su.detail

import androidx.lifecycle.AndroidViewModel
import com.blood.tears.rhododendron.red.bean.AllUtils
import com.blood.tears.rhododendron.red.bean.AppUtils
import com.blood.tears.rhododendron.red.bean.DataUtilsHelp
import com.blood.tears.rhododendron.red.bean.SDataBean
import com.blood.tears.rhododendron.red.app.App
import com.blood.tears.rhododendron.red.two.BaseViewModel

class SugarDetailViewModel: BaseViewModel() {
    private var currentUnit = AllUtils.BloodSugarUnit.L.toString()

    fun loadExistingData(sugarId: String): SDataBean? {
        return AllUtils.getSugarListData().find { it.id == sugarId }
    }

    fun createSugarBean(
        numL: Double,
        numDL: Double,
        date: String,
        currentState: AllUtils.CurrentState,
        id: String
    ): SDataBean {
        return SDataBean(
            numL = numL,
            numDL = numDL,
            date = date,
            currentState = currentState,
            state = AppUtils.determineBloodSugarStatus(currentState, numL),
            id = id
        )
    }

    fun updateBloodSugarStatus(currentState: AllUtils.CurrentState, num: Double): String {
        return AppUtils.determineBloodSugarStatus(currentState, getSugarNumToL(num)).toString()
    }

    fun toggleSugarUnit(): String {
        currentUnit = if (currentUnit == AllUtils.BloodSugarUnit.DL.toString()) {
            AllUtils.BloodSugarUnit.L.toString()
        } else {
            AllUtils.BloodSugarUnit.DL.toString()
        }
        DataUtilsHelp.sugarUnit = currentUnit
        return getCurrentSugarUnitText()
    }

    fun setSugarUnit(){
        currentUnit = DataUtilsHelp.sugarUnit
    }

    fun convertSugarNum(num: Double): Double {
        return if (currentUnit == AllUtils.BloodSugarUnit.DL.toString()) {
            AllUtils.convertLToDl(num)
        } else {
            AllUtils.convertDlToL(num)
        }
    }

    fun getSugarNumToL(num: Double): Double {
        return if (currentUnit == AllUtils.BloodSugarUnit.DL.toString()) {
            AllUtils.convertDlToL(num)
        } else {
            num
        }
    }

    fun getSugarNumToDl(num: Double): Double {
        return if (currentUnit == AllUtils.BloodSugarUnit.L.toString()) {
            AllUtils.convertLToDl(num)
        } else {
            num
        }
    }

    fun getCurrentSugarUnit(): String {
        return currentUnit
    }

    fun getCurrentSugarUnitText(): String {
        return if (currentUnit == AllUtils.BloodSugarUnit.DL.toString()) {
            "mg/dl"
        } else {
            "mmol/l"
        }
    }

    fun getSugarNumText(bean: SDataBean): Double {
        return if (currentUnit == AllUtils.BloodSugarUnit.DL.toString()) {
            bean.numDL
        } else {
            bean.numL
        }
    }

    fun getColorInt(state: String): Int {
        return when (state) {
            "LOW" -> 1
            "NORMAL" -> 2
            "PREDIABETES" -> 3
            else -> 4
        }
    }

    fun getCurrentStateInt(currentState: String): Int {
        return when (currentState) {
            "NORMAL" -> 1
            "BEFORE_MEAL" -> 2
            "BEFORE_EXERCISE" -> 3
            "AFTER_EXERCISE" -> 4
            "ONE_HOUR_AFTER_MEAL" -> 5
            "ASLEEP" -> 6
            "TWO_HOURS_AFTER_MEAL" -> 7
            "FASTING" -> 8
            else -> 1
        }
    }
}