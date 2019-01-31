package com.zacharysweigart.weatherapp

import android.app.Application
import com.facebook.stetho.Stetho

class MainApp : Application() {

    companion object {
        lateinit var instance: MainApp
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        Stetho.initializeWithDefaults(this)
    }
}