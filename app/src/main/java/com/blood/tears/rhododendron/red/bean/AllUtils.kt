package com.blood.tears.rhododendron.red.bean

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object AllUtils {
    private val gson = Gson()


    enum class BloodSugarUnit {
        DL, L
    }

    enum class BloodSugarStatus {
        LOW, NORMAL, PREDIABETES, DIABETES
    }

    enum class CurrentState {
        NORMAL,
        BEFORE_EXERCISE,
        BEFORE_MEAL,
        FASTING,
        ONE_HOUR_AFTER_MEAL,
        TWO_HOURS_AFTER_MEAL,
        AFTER_EXERCISE,
        ASLEEP
    }
    private inline fun <reified T> getListFromJson(json: String): MutableList<T> {
        val type = object : TypeToken<MutableList<T>>() {}.type
        return runCatching { gson.fromJson<MutableList<T>>(json, type) }.getOrNull() ?: mutableListOf()
    }

    private inline fun <reified T> saveListToJson(list: MutableList<T>, jsonHolder: (String) -> Unit) {
        jsonHolder(gson.toJson(list))
    }

    fun saveSugar(pressure: SDataBean) {
        val list = getListFromJson<SDataBean>(DataUtilsHelp.sugarJson)
        list.add(pressure)
        saveListToJson(list) { DataUtilsHelp.sugarJson = it }
    }

    fun updateSugar(pressure: SDataBean) {
        val list = getListFromJson<SDataBean>(DataUtilsHelp.sugarJson)
        list.find { it.id == pressure.id }?.let {
            list[list.indexOf(it)] = pressure
            saveListToJson(list) { DataUtilsHelp.sugarJson = it }
        }
    }

    fun getSugarListData(): MutableList<SDataBean> {
        return getListFromJson<SDataBean>(DataUtilsHelp.sugarJson).sortedByDescending { it.id }.toMutableList()
    }

    fun savePressure(pressure: PDataBean) {
        val list = getListFromJson<PDataBean>(DataUtilsHelp.pressureJson)
        list.add(pressure)
        saveListToJson(list) { DataUtilsHelp.pressureJson = it }
    }

    fun updatePressure(pressure: PDataBean) {
        val list = getListFromJson<PDataBean>(DataUtilsHelp.pressureJson)
        list.find { it.id == pressure.id }?.let {
            list[list.indexOf(it)] = pressure
            saveListToJson(list) { DataUtilsHelp.pressureJson = it }
        }
    }

    fun getPressureListData(): MutableList<PDataBean> {
        return getListFromJson<PDataBean>(DataUtilsHelp.pressureJson).sortedByDescending { it.id }.toMutableList()
    }
    fun convertDlToL(value: Double): Double {
        return value * 0.0555
    }

    fun convertLToDl(value: Double): Double {
        return value / 0.0555
    }


    fun closeKeyboard(activity: AppCompatActivity) {
        val view = activity.window.peekDecorView()
        if (view != null) {
            val inputMethodManager =
                activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}