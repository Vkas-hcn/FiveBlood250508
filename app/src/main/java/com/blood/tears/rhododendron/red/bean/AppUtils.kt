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
        currentState: AllUtils.CurrentState,
        bloodSugarValue: Double
    ): AllUtils.BloodSugarStatus {
        return if (currentState == AllUtils.CurrentState.NORMAL ||
            currentState == AllUtils.CurrentState.BEFORE_EXERCISE ||
            currentState == AllUtils.CurrentState.BEFORE_MEAL ||
            currentState == AllUtils.CurrentState.FASTING ||
            currentState == AllUtils.CurrentState.AFTER_EXERCISE ||
            currentState == AllUtils.CurrentState.ASLEEP
        ) {
            if (bloodSugarValue < 4) {
                AllUtils.BloodSugarStatus.LOW
            } else if (bloodSugarValue < 5.5) {
                AllUtils.BloodSugarStatus.NORMAL
            } else if (bloodSugarValue < 7) {
                AllUtils.BloodSugarStatus.PREDIABETES
            } else {
                AllUtils.BloodSugarStatus.DIABETES
            }
        } else if (currentState == AllUtils.CurrentState.ONE_HOUR_AFTER_MEAL) {
            if (bloodSugarValue < 4) {
                AllUtils.BloodSugarStatus.LOW
            } else if (bloodSugarValue < 7.8) {
                AllUtils.BloodSugarStatus.NORMAL
            } else if (bloodSugarValue < 8.5) {
                AllUtils.BloodSugarStatus.PREDIABETES
            } else {
                AllUtils.BloodSugarStatus.DIABETES
            }
        } else if (currentState == AllUtils.CurrentState.TWO_HOURS_AFTER_MEAL) {
            if (bloodSugarValue < 4) {
                AllUtils.BloodSugarStatus.LOW
            } else if (bloodSugarValue < 4.7) {
                AllUtils.BloodSugarStatus.NORMAL
            } else if (bloodSugarValue < 7) {
                AllUtils.BloodSugarStatus.PREDIABETES
            } else {
                AllUtils.BloodSugarStatus.DIABETES
            }
        } else {
            AllUtils.BloodSugarStatus.NORMAL // 默认值
        }
    }

    fun getSugarStateImage(bloodSugarStatus: AllUtils.BloodSugarStatus?, stringInt: String?): Int {
        return if (stringInt != null) {
            if (stringInt == AllUtils.BloodSugarStatus.LOW.toString()) {
                R.drawable.circle_1
            } else if (stringInt == AllUtils.BloodSugarStatus.NORMAL.toString()) {
                R.drawable.circle_2
            } else if (stringInt == AllUtils.BloodSugarStatus.PREDIABETES.toString()) {
                R.drawable.circle_4
            } else if (stringInt == AllUtils.BloodSugarStatus.DIABETES.toString()) {
                R.drawable.circle_6
            } else {
                R.drawable.circle_2
            }
        } else {
            if (bloodSugarStatus == AllUtils.BloodSugarStatus.LOW) {
                R.drawable.icon_1
            } else if (bloodSugarStatus == AllUtils.BloodSugarStatus.NORMAL) {
                R.drawable.icon_2
            } else if (bloodSugarStatus == AllUtils.BloodSugarStatus.PREDIABETES) {
                R.drawable.icon_4
            } else if (bloodSugarStatus == AllUtils.BloodSugarStatus.DIABETES) {
                R.drawable.icon_6
            } else {
                R.drawable.icon_2
            }
        }
    }

    fun getSugarStateColor(bloodSugarStatus: AllUtils.BloodSugarStatus): Int {
        return if (bloodSugarStatus == AllUtils.BloodSugarStatus.LOW) {
            Color.parseColor("#418AE0")
        } else if (bloodSugarStatus == AllUtils.BloodSugarStatus.NORMAL) {
            Color.parseColor("#3AC34A")
        } else if (bloodSugarStatus == AllUtils.BloodSugarStatus.PREDIABETES) {
            Color.parseColor("#EE9102")
        } else if (bloodSugarStatus == AllUtils.BloodSugarStatus.DIABETES) {
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

    fun getSugarCurrentState(currentState: AllUtils.CurrentState): String {
        return if (currentState == AllUtils.CurrentState.NORMAL) {
            "Normal"
        } else if (currentState == AllUtils.CurrentState.BEFORE_EXERCISE) {
            "Before Exercise"
        } else if (currentState == AllUtils.CurrentState.BEFORE_MEAL) {
            "Before Meal"
        } else if (currentState == AllUtils.CurrentState.FASTING) {
            "Fasting"
        } else if (currentState == AllUtils.CurrentState.ONE_HOUR_AFTER_MEAL) {
            "1HourAfterMeal"
        } else if (currentState == AllUtils.CurrentState.TWO_HOURS_AFTER_MEAL) {
            "2HoursAfterMeal"
        } else if (currentState == AllUtils.CurrentState.AFTER_EXERCISE) {
            "AfterExercise"
        } else if (currentState == AllUtils.CurrentState.ASLEEP) {
            "Sleep"
        } else {
            "Unknown" // 默认值
        }
    }
}
