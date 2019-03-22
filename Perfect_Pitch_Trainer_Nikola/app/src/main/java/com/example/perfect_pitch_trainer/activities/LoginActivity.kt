package com.example.perfect_pitch_trainer.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import com.example.perfect_pitch_trainer.R
import kotlinx.coroutines.*
import java.net.URL

class LoginActivity : AppCompatActivity() {


    lateinit  var btRegisterActivity: Button
    lateinit  var tvForgotPassword: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btRegisterActivity = findViewById(R.id.btRegisterActivity)
        tvForgotPassword = findViewById(R.id.tvForgotPassword)



        btRegisterActivity.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {

                val randomIntent = Intent (this@LoginActivity, RegisterActivity::class.java)
                // Start the new activity.
                startActivity(randomIntent)
            }
        })

        tvForgotPassword.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {

                val randomIntent = Intent (this@LoginActivity, ForgotPasswordActivity::class.java)
                // Start the new activity.
                startActivity(randomIntent)
            }
        })

    }


}
