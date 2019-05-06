package com.example.perfectpitchcoach

import android.app.Application

class App : Application() {
    companion object {
        @JvmStatic
        lateinit var ref: App
    }

    init {
        ref = this
    }
}