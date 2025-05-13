package com.blood.tears.rhododendron.red.bean

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object AllUtils {
    const val DL = "DL"
    const val L = "L"
//    enum class BloodSugarUnit {
//        DL, L
//    }

//    enum class BloodSugarStatus {
//        LOW, NORMAL, PREDIABETES, DIABETES
//    }
    const val LOW = "LOW"
    const val NORMAL = "NORMAL"
    const val PREDIABETES = "PREDIABETES"
    const val DIABETES = "DIABETES"

//    enum class CurrentState {
//        NORMAL,
//        BEFORE_EXERCISE,
//        BEFORE_MEAL,
//        FASTING,
//        ONE_HOUR_AFTER_MEAL,
//        TWO_HOURS_AFTER_MEAL,
//        AFTER_EXERCISE,
//        ASLEEP
//    }
    const val BEFORE_EXERCISE = "BEFORE_EXERCISE"
    const val BEFORE_MEAL = "BEFORE_MEAL"
    const val FASTING = "FASTING"
    const val ONE_HOUR_AFTER_MEAL = "ONE_HOUR_AFTER_MEAL"
    const val TWO_HOURS_AFTER_MEAL = "TWO_HOURS_AFTER_MEAL"
    const val AFTER_EXERCISE = "AFTER_EXERCISE"
    const val ASLEEP = "ASLEEP"
    private inline fun <reified T> getListFromJson(json: String): MutableList<T> {
        val type = object : TypeToken<MutableList<T>>() {}.type
        return runCatching { Gson().fromJson<MutableList<T>>(json, type) }.getOrNull() ?: mutableListOf()
    }

    private inline fun <reified T> saveListToJson(list: MutableList<T>, jsonHolder: (String) -> Unit) {
        jsonHolder(Gson().toJson(list))
    }
    inline fun <reified T> String.toMutableList(): MutableList<T> {
        val type = object : TypeToken<MutableList<T>>() {}.type
        return runCatching { Gson().fromJson<MutableList<T>>(this, type) }.getOrNull() ?: mutableListOf()
    }

    inline fun <reified T> MutableList<T>.toJsonString(block: (String) -> Unit) {
        block(Gson().toJson(this))
    }
    fun SDataBean.saveSugar() {
        val list = DataUtilsHelp.sugarJson.toMutableList<SDataBean>()
        list.add(this)
        list.toJsonString { DataUtilsHelp.sugarJson = it }
    }

    fun SDataBean.updateSugar() {
        DataUtilsHelp.sugarJson.toMutableList<SDataBean>().apply {
            find { it.id == this@updateSugar.id }?.let {
                this[indexOf(it)] = this@updateSugar
                toJsonString { DataUtilsHelp.sugarJson = it }
            }
        }
    }

    fun getSugarListData() = DataUtilsHelp.sugarJson.toMutableList<SDataBean>()
        .sortedByDescending { it.id }
        .toMutableList()









//    fun saveSugar(pressure: SDataBean) {
//        val list = getListFromJson<SDataBean>(DataUtilsHelp.sugarJson)
//        list.add(pressure)
//        saveListToJson(list) { DataUtilsHelp.sugarJson = it }
//    }
//
//    fun updateSugar(pressure: SDataBean) {
//        val list = getListFromJson<SDataBean>(DataUtilsHelp.sugarJson)
//        list.find { it.id == pressure.id }?.let {
//            list[list.indexOf(it)] = pressure
//            saveListToJson(list) { DataUtilsHelp.sugarJson = it }
//        }
//    }

//    fun getSugarListData(): MutableList<SDataBean> {
//        return getListFromJson<SDataBean>(DataUtilsHelp.sugarJson).sortedByDescending { it.id }.toMutableList()
//    }

//    fun savePressure(pressure: PDataBean) {
//        val list = getListFromJson<PDataBean>(DataUtilsHelp.pressureJson)
//        list.add(pressure)
//        saveListToJson(list) { DataUtilsHelp.pressureJson = it }
//    }
//
//    fun updatePressure(pressure: PDataBean) {
//        val list = getListFromJson<PDataBean>(DataUtilsHelp.pressureJson)
//        list.find { it.id == pressure.id }?.let {
//            list[list.indexOf(it)] = pressure
//            saveListToJson(list) { DataUtilsHelp.pressureJson = it }
//        }
//    }
//
//    fun getPressureListData(): MutableList<PDataBean> {
//        return getListFromJson<PDataBean>(DataUtilsHelp.pressureJson).sortedByDescending { it.id }.toMutableList()
//    }

    fun PDataBean.savePressure() {
        val list = DataUtilsHelp.pressureJson.toMutableList<PDataBean>()
        list.add(this)
        list.toJsonString { DataUtilsHelp.pressureJson = it }
    }

    fun PDataBean.updatePressure() {
        DataUtilsHelp.pressureJson.toMutableList<PDataBean>().apply {
            find { it.id == this@updatePressure.id }?.let {
                this[indexOf(it)] = this@updatePressure
                toJsonString { DataUtilsHelp.pressureJson = it }
            }
        }
    }

    fun getPressureListData() = DataUtilsHelp.pressureJson.toMutableList<PDataBean>()
        .sortedByDescending { it.id }
        .toMutableList()




    fun convertDlToL(value: Double): Double {
        return value * 0.0555
    }

    fun convertLToDl(value: Double): Double {
        return value / 0.0555
    }


//    fun closeKeyboard(activity: AppCompatActivity) {
//        val view = activity.window.peekDecorView()
//        if (view != null) {
//            val inputMethodManager =
//                activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
//        }
//    }

    fun AppCompatActivity.closeKeyboard() {
        window.peekDecorView()?.let {
            (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
                .hideSoftInputFromWindow(it.windowToken, 0)
        }
    }
}