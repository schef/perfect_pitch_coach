package com.example.perfectpitchcoach

import android.app.Application
import com.example.perfect_pitch_trainer.settings.AppPreferences

class App : Application() {
    companion object {
        @JvmStatic
        lateinit var ref: App
    }

    init {
        ref = this
    }

    override fun onCreate() {
        super.onCreate()
        AppPreferences.init(this)
    }
}