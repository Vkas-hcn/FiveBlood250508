package com.blood.tears.rhododendron.red.bean

import android.content.Context
import com.blood.tears.rhododendron.red.app.App


object DataUtilsHelp {
    private const val PREFS_NAME = "local_data_prefs"
    private const val KEY_PRESSURE_JSON = "pressureJson"
    private const val KEY_SUGAR_JSON = "sugarJson"
    private const val KEY_SUGAR_UNIT = "sugarUnit"

    private val context by lazy { App.appComponent }
    private val sharedPreferences by lazy { context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE) }

    @Synchronized
    private fun readData(): LSDBean {
        return LSDBean(
            pressureJson = sharedPreferences.getString(KEY_PRESSURE_JSON, "") ?: "",
            sugarJson = sharedPreferences.getString(KEY_SUGAR_JSON, "") ?: "",
            sugarUnit = sharedPreferences.getString(KEY_SUGAR_UNIT, "") ?: ""
        )
    }

    @Synchronized
    private fun saveData(data: LSDBean) {
        with(sharedPreferences.edit()) {
            putString(KEY_PRESSURE_JSON, data.pressureJson)
            putString(KEY_SUGAR_JSON, data.sugarJson)
            putString(KEY_SUGAR_UNIT, data.sugarUnit)
            apply()
        }
    }

    var pressureJson: String
        get() = readData().pressureJson
        set(value) {
            val current = readData()
            current.pressureJson = value
            saveData(current)
        }

    var sugarJson: String
        get() = readData().sugarJson
        set(value) {
            val current = readData()
            current.sugarJson = value
            saveData(current)
        }

    var sugarUnit: String
        get() = readData().sugarUnit
        set(value) {
            val current = readData()
            current.sugarUnit = value
            saveData(current)
        }
}