package com.blood.tears.rhododendron.red.app

import android.app.Application

class App : Application() {
    companion object {
        lateinit var appComponent: Application
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = this
    }
}