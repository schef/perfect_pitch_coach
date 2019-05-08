package com.example.perfectpitchcoach.activities

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import com.example.brc.kotlintestapp.Settings.RetrofitUserService
import com.example.perfect_pitch_trainer.model.LoginRequest
import com.example.perfect_pitch_trainer.model.LoginResponse
import com.example.perfect_pitch_trainer.settings.AppPreferences
import com.example.perfect_pitch_trainer.util.NetworkUtil
import com.example.perfectpitchcoach.R
import kotlinx.coroutines.*
import ru.gildor.coroutines.retrofit.await

class LoginActivity : AppCompatActivity() {

    lateinit var context: Context

    lateinit  var btLogin: Button
    lateinit var  etUsername: EditText
    lateinit var  etPassword: EditText
    lateinit  var btRegisterActivity: Button
    lateinit  var tvForgotPassword: TextView
    lateinit var  tvBackendResponseErrorMessage: TextView
    lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        context = this

        btLogin = findViewById(R.id.btLogin)
        etUsername = findViewById(R.id.etUsername)
        etPassword = findViewById(R.id.etPassword)
        btRegisterActivity = findViewById(R.id.btRegisterActivity)
        tvForgotPassword = findViewById(R.id.tvForgotPassword)
        tvBackendResponseErrorMessage = findViewById(R.id.tvBackendResponseErrorMessage)
        progressBar = findViewById(R.id.progressBar)

        val extras = getIntent().getExtras()
        if (extras != null) {
            val reponseBackend = extras.getString("backendResponseSuccess")
            val reponseBackendMessage = extras.getString("backendResponseMessage")

            tvBackendResponseErrorMessage.setTextColor( ContextCompat.getColor( context, R.color.colorPrimary))
            tvBackendResponseErrorMessage.visibility = View.VISIBLE
            tvBackendResponseErrorMessage.text = reponseBackendMessage

            if( extras.getString("username") != "" ) {
                etUsername.setText(extras.getString("username"))
                etPassword.setText(extras.getString("password"))
            }
        }


        btRegisterActivity.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {

                val randomIntent = Intent (this@LoginActivity, RegisterActivity::class.java)
                finish()
                startActivity(randomIntent)

            }
        })

        tvForgotPassword.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {

                val randomIntent = Intent (this@LoginActivity, ForgotPasswordActivity::class.java)
                finish()
                startActivity(randomIntent)
            }
        })

        btLogin.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {

                if( NetworkUtil.checkIfUserHasInternetConnection(context ) ) {

                    tvBackendResponseErrorMessage.setTextColor( ContextCompat.getColor( context, android.R.color.holo_red_dark))
                    if( etUsername.text.toString() == "" ) {

                        tvBackendResponseErrorMessage.visibility = View.VISIBLE
                        tvBackendResponseErrorMessage.text = "You did not fill username"
                    }
                    else if( etPassword.text.toString() == "" ) {

                        tvBackendResponseErrorMessage.visibility = View.VISIBLE
                        tvBackendResponseErrorMessage.text = "You did not fill password"
                    }
                    else if( etUsername.text.length > 24 ) {

                        tvBackendResponseErrorMessage.visibility = View.VISIBLE
                        tvBackendResponseErrorMessage.text = "Your password is to large, maximum allowed number of characters are 25. " +
                                "You have " +  etUsername.text.length + " charachters"
                    }
                    else if( etPassword.text.length > 24 ) {

                        tvBackendResponseErrorMessage.visibility = View.VISIBLE
                        tvBackendResponseErrorMessage.text = "Your password is to large, maximum allowed number of characters are 25. " +
                                "You have " +  etPassword.text.length + " charachters"
                    }
                    else {
                        progressBar.visibility = View.VISIBLE
                        val newLoginUser: LoginRequest = LoginRequest()
                        newLoginUser.username = etUsername.text.toString()
                        newLoginUser.password = etPassword.text.toString()

                        registerUserSecondExampleRetrofit(newLoginUser)
                    }
                }
                else {
                    tvBackendResponseErrorMessage.visibility = View.VISIBLE
                    tvBackendResponseErrorMessage.text = "You don't have internet connection"
                }
            }
        })

    }

    private fun registerUserSecondExampleRetrofit(newLoginUser: LoginRequest) {

        val retrofitService = RetrofitUserService().getRetrofit(applicationContext)

        var result3: LoginResponse = LoginResponse()

        GlobalScope.launch {

            try {

                val work2 = async { retrofitService.getLoginUserData(newLoginUser)  }

                result3 =work2.await().await()

                withContext(Dispatchers.Main) {

                    progressBar.visibility = View.GONE
                    if( result3?.success == true ) {
                        Log.d("LoginActivity", "2" + result3?.message)

                        AppPreferences.userLoggedIn = true
                        //do something with result
                        val randomIntent = Intent(this@LoginActivity, MainActivity::class.java)
                        randomIntent.putExtra("backendResponseSuccess", result3?.success)
                        randomIntent.putExtra("backendResponseMessage", result3?.message)

                        startActivity(randomIntent)

                        finish()
                    }
                    else {
                        tvBackendResponseErrorMessage.visibility = View.VISIBLE
                        tvBackendResponseErrorMessage.text = result3?.message
                    }
                }

            } catch (exception: Exception) {

                withContext(Dispatchers.Main) {

                    progressBar.visibility = View.GONE
                    tvBackendResponseErrorMessage.visibility = View.VISIBLE
                    tvBackendResponseErrorMessage.text = result3?.message
                }

                exception.printStackTrace()
                exception.printStackTrace()
            }
        }

    }


}
