package com.example.perfect_pitch_trainer.settings

import android.app.Application

class PerfetchPitchApp : Application() {

    override fun onCreate() {
        super.onCreate()
        AppPreferences.init(this)
    }
}