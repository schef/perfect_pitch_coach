package com.example.perfect_pitch_trainer.activities

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ProgressBar
import com.example.perfect_pitch_trainer.MainActivity
import com.example.perfect_pitch_trainer.R

class SplashScreen : AppCompatActivity() {

    lateinit  var progressBar: ProgressBar
    private val SPLASH_DISPLAY_LENGTH = 1500

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        progressBar = findViewById(R.id.progressBar)

        Handler().postDelayed({
            /* Create an Intent that will start the MainActivity. */
            progressBar.visibility = View.GONE
            val mainIntent = Intent(this@SplashScreen, LoginActivity::class.java)
            startActivity(mainIntent)
            finish()
        }, SPLASH_DISPLAY_LENGTH.toLong())


    }
}
