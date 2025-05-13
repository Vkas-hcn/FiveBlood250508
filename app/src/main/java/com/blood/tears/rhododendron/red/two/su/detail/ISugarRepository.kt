package com.blood.tears.rhododendron.red.two.su.detail

import com.blood.tears.rhododendron.red.bean.AllUtils
import com.blood.tears.rhododendron.red.bean.AllUtils.saveSugar
import com.blood.tears.rhododendron.red.bean.AllUtils.updateSugar
import com.blood.tears.rhododendron.red.bean.DataUtilsHelp
import com.blood.tears.rhododendron.red.bean.SDataBean

// ISugarRepository.kt
interface ISugarRepository {
    fun getSugarList(): List<SDataBean>
    fun saveSugar(item: SDataBean)
    fun updateSugar(item: SDataBean)
    fun getCurrentUnit(): String
    fun saveUnit(unit: String)
}

// SugarRepositoryImpl.kt
class SugarRepositoryImpl : ISugarRepository {
    override fun getSugarList() = AllUtils.getSugarListData()

    override fun saveSugar(item: SDataBean) = item.saveSugar()

    override fun updateSugar(item: SDataBean) = item.updateSugar()

    override fun getCurrentUnit() = DataUtilsHelp.sugarUnit

    override fun saveUnit(unit: String) {
        DataUtilsHelp.sugarUnit = unit
    }
}
