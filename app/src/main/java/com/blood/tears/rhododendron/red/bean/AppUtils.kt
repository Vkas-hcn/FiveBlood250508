package com.blood.tears.rhododendron.red.bean

import android.annotation.SuppressLint
import android.graphics.Color
import com.blood.tears.rhododendron.red.R
import java.text.SimpleDateFormat
import java.util.Date



object AppUtils {

    fun checkBloodPressure(systolic: Int, diastolic: Int): Int {
        return if (systolic < 90 || diastolic < 60) {
            1
        } else if (systolic in 90..119 && diastolic in 60..79) {
            2
        } else if (systolic in 120..129 && diastolic < 80) {
            3
        } else if (systolic in 130..139 || diastolic in 80..89) {
            4
        } else if (systolic in 140..180 || diastolic in 90..120) {
            5
        } else if (systolic >= 180 || diastolic >= 120) {
            6
        } else {
            1
        }
    }

    fun getBloodPressureState(state: Int): String {
        return if (state == 1) {
            "Hypotension"
        } else if (state == 2) {
            "Normal"
        } else if (state == 3) {
            "Elevated"
        } else if (state == 4) {
            "Hypertension Stage 1"
        } else if (state == 5) {
            "Hypertension Stage 2"
        } else if (state == 6) {
            "Hypertension Crisis"
        } else {
            "Invalid"
        }
    }

    fun getBloodPressureStateColor(state: Int): Int {
        return if (state == 1) {
            Color.parseColor("#0180F8")
        } else if (state == 2) {
            Color.parseColor("#3AC34A")
        } else if (state == 3) {
            Color.parseColor("#DBB60A")
        } else if (state == 4) {
            Color.parseColor("#EE9102")
        } else if (state == 5) {
            Color.parseColor("#F57602")
        } else if (state == 6) {
            Color.parseColor("#FE5001")
        } else {
            Color.parseColor("#0180F8")
        }
    }

    fun getBloodPressureStateImage(state: Int): Int {
        return if (state == 1) {
            R.drawable.icon_1
        } else if (state == 2) {
            R.drawable.icon_2
        } else if (state == 3) {
            R.drawable.icon_3
        } else if (state == 4) {
            R.drawable.icon_4
        } else if (state == 5) {
            R.drawable.icon_5
        } else if (state == 6) {
            R.drawable.icon_6
        } else {
            R.drawable.icon_2
        }
    }

    @SuppressLint("SimpleDateFormat")
    fun getDateTime(currentTimeMillis: String): String {
        return try {
            val now1 = Date(currentTimeMillis.toLong())
            val format = SimpleDateFormat("yyyy-MM-dd HH:mm")
            format.format(now1)
        } catch (e: Exception) {
            "" // 返回空字符串或其他默认值
        }
    }

    fun determineBloodSugarStatus(
        currentState: String,
        bloodSugarValue: Double
    ): String {
        return if (currentState == AllUtils.NORMAL ||
            currentState == AllUtils.BEFORE_EXERCISE ||
            currentState == AllUtils.BEFORE_MEAL ||
            currentState == AllUtils.FASTING ||
            currentState == AllUtils.AFTER_EXERCISE ||
            currentState == AllUtils.ASLEEP
        ) {
            if (bloodSugarValue < 4) {
                AllUtils.LOW
            } else if (bloodSugarValue < 5.5) {
                AllUtils.NORMAL
            } else if (bloodSugarValue < 7) {
                AllUtils.PREDIABETES
            } else {
                AllUtils.DIABETES
            }
        } else if (currentState == AllUtils.ONE_HOUR_AFTER_MEAL) {
            if (bloodSugarValue < 4) {
                AllUtils.LOW
            } else if (bloodSugarValue < 7.8) {
                AllUtils.NORMAL
            } else if (bloodSugarValue < 8.5) {
                AllUtils.PREDIABETES
            } else {
                AllUtils.DIABETES
            }
        } else if (currentState == AllUtils.TWO_HOURS_AFTER_MEAL) {
            if (bloodSugarValue < 4) {
                AllUtils.LOW
            } else if (bloodSugarValue < 4.7) {
                AllUtils.NORMAL
            } else if (bloodSugarValue < 7) {
                AllUtils.PREDIABETES
            } else {
                AllUtils.DIABETES
            }
        } else {
            AllUtils.NORMAL // 默认值
        }
    }

    fun getSugarStateImage(bloodSugarStatus: String?, stringInt: String?): Int {
        return if (stringInt != null) {
            if (stringInt == AllUtils.LOW.toString()) {
                R.drawable.circle_1
            } else if (stringInt == AllUtils.NORMAL.toString()) {
                R.drawable.circle_2
            } else if (stringInt == AllUtils.PREDIABETES.toString()) {
                R.drawable.circle_4
            } else if (stringInt == AllUtils.DIABETES.toString()) {
                R.drawable.circle_6
            } else {
                R.drawable.circle_2
            }
        } else {
            if (bloodSugarStatus == AllUtils.LOW) {
                R.drawable.icon_1
            } else if (bloodSugarStatus == AllUtils.NORMAL) {
                R.drawable.icon_2
            } else if (bloodSugarStatus == AllUtils.PREDIABETES) {
                R.drawable.icon_4
            } else if (bloodSugarStatus == AllUtils.DIABETES) {
                R.drawable.icon_6
            } else {
                R.drawable.icon_2
            }
        }
    }

    fun getSugarStateColor(bloodSugarStatus: String): Int {
        return if (bloodSugarStatus == AllUtils.LOW) {
            Color.parseColor("#418AE0")
        } else if (bloodSugarStatus == AllUtils.NORMAL) {
            Color.parseColor("#3AC34A")
        } else if (bloodSugarStatus == AllUtils.PREDIABETES) {
            Color.parseColor("#EE9102")
        } else if (bloodSugarStatus == AllUtils.DIABETES) {
            Color.parseColor("#FE5001")
        } else {
            Color.parseColor("#3AC34A") // 默认值
        }
    }

    fun getSugarStateColorString(bloodSugarStatus: String): Int {
        return if (bloodSugarStatus == "LOW") {
            Color.parseColor("#418AE0")
        } else if (bloodSugarStatus == "NORMAL") {
            Color.parseColor("#3AC34A")
        } else if (bloodSugarStatus == "PREDIABETES") {
            Color.parseColor("#EE9102")
        } else if (bloodSugarStatus == "DIABETES") {
            Color.parseColor("#FE5001")
        } else {
            Color.parseColor("#3AC34A") // 默认值
        }
    }

    fun getSugarCurrentState(currentState: String): String {
        return if (currentState == AllUtils.NORMAL) {
            "Normal"
        } else if (currentState == AllUtils.BEFORE_EXERCISE) {
            "Before Exercise"
        } else if (currentState == AllUtils.BEFORE_MEAL) {
            "Before Meal"
        } else if (currentState == AllUtils.FASTING) {
            "Fasting"
        } else if (currentState == AllUtils.ONE_HOUR_AFTER_MEAL) {
            "1HourAfterMeal"
        } else if (currentState == AllUtils.TWO_HOURS_AFTER_MEAL) {
            "2HoursAfterMeal"
        } else if (currentState == AllUtils.AFTER_EXERCISE) {
            "AfterExercise"
        } else if (currentState == AllUtils.ASLEEP) {
            "Sleep"
        } else {
            "Unknown" // 默认值
        }
    }
}
