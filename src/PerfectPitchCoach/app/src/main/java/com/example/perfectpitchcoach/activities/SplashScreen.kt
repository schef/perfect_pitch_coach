package com.example.perfectpitchcoach.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.perfectpitchcoach.R

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val mainIntent = Intent(this@SplashScreen, MainActivity::class.java)
        startActivity(mainIntent)
        finish()
    }
}
