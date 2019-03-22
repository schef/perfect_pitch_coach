package com.example.perfect_pitch_trainer.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.Contacts
import android.text.Html
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.brc.kotlintestapp.Settings.RetrofitService
import com.example.perfect_pitch_trainer.MainActivity
import com.example.perfect_pitch_trainer.R
import com.example.perfect_pitch_trainer.model.RegisterDAO
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.*
import okhttp3.*
import ru.gildor.coroutines.retrofit.awaitResponse
import java.io.IOException
import java.io.InputStream
import java.net.URL
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.HttpsURLConnection

class RegisterActivity : AppCompatActivity() {


    lateinit  var etName: EditText
    lateinit  var etSurname: EditText
    lateinit  var etEmail: EditText
    lateinit  var etUsername: EditText
    lateinit  var etPassword: EditText
    lateinit  var btRegisterUser: Button
    private val client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        etName = findViewById(R.id.etName)
        etSurname = findViewById(R.id.etSurname)
        etEmail = findViewById(R.id.etEmail)
        etUsername = findViewById(R.id.etUsername)
        etPassword = findViewById(R.id.etPassword)
        btRegisterUser = findViewById(R.id.btRegisterUser)

        //registerUserFirstExampleOkHttp()

        btRegisterUser.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                val newRegisteredUser: RegisterDAO = RegisterDAO()
                newRegisteredUser.name = etName.text.toString()
                newRegisteredUser.surname = etSurname.text.toString()
                newRegisteredUser.email = etEmail.text.toString()
                newRegisteredUser.username = etUsername.text.toString()
                newRegisteredUser.password = etPassword.text.toString()

                registerUserSecondExampleRetrofit(newRegisteredUser)

                //registerUserThirdExampleHttpsUrlConnection(newRegisteredUser)
            }
        })


    }

    private fun registerUserThirdExampleHttpsUrlConnection(newRegisteredUser: RegisterDAO) {

        GlobalScope.launch {

            try {

                /* val deferred1 = async { calculateHardThings(10, "11111") }
                val deferred2 = async { calculateHardThings(10, "22222") }

                val sum = deferred1.await() + deferred2.await() */

                val hostnameVerifier = HostnameVerifier { _, session ->
                    HttpsURLConnection.getDefaultHostnameVerifier().run {
                        verify("https://192.168.1.5:5000/", session)
                    }
                }

// Tell the URLConnection to use our HostnameVerifier
                val url = URL("https://192.168.1.5:5000/signUpUser")
                val urlConnection = url.openConnection() as HttpsURLConnection
                urlConnection.hostnameVerifier = hostnameVerifier
                val inputStream: InputStream = urlConnection.inputStream
                //copyInputStreamToOutputStream(inputStream, System.out)

                withContext(Dispatchers.Main) {

                    Log.d("Ispis https request", inputStream.toString())
                    //var hashMapAppTerms = MyApplication.instance.items

                    //do something with result
                    val randomIntent = Intent(this@RegisterActivity, LoginActivity::class.java)
                    // Start the new activity.
                    startActivity(randomIntent)

                    finish()
                }

            } catch (exception: Exception) {
                exception.printStackTrace()
            }
        }


    }

    private fun registerUserFirstExampleOkHttp() {
        GlobalScope.launch {
            val beginTimestamp = System.currentTimeMillis()
            val duration = System.currentTimeMillis() - beginTimestamp
            Log.d("", "App Start length:" + duration)
            if (duration < 1000) {
                delay(1000 - duration)
            }

            val newRegisteredUser: RegisterDAO = RegisterDAO()
            newRegisteredUser.name = etName.text.toString()
            newRegisteredUser.surname = etSurname.text.toString()
            newRegisteredUser.email = etEmail.text.toString()
            newRegisteredUser.username = etUsername.text.toString()
            newRegisteredUser.password = etPassword.text.toString()

            val formBody = FormBody.Builder()
                .add("name", etName.text.toString())
                .add("surname", etSurname.text.toString())
                .add("email", etEmail.text.toString())
                .add("username", etUsername.text.toString())
                .add("password", etPassword.text.toString())
                .build()
            //val body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), newRegisteredUser)


            val request = Request.Builder()
                .url("https://localhost:5000/signUpUser")
                //.post(body)
                .post(formBody)
                .build()

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {}
                override fun onResponse(call: Call, response: Response) = println(response.body()?.string())
            })

            withContext(Dispatchers.Main) {
                // startApp()
                //val randomIntent = Intent (this@RegisterActivity, MainActivity::class.java)
                // Start the new activity.
                // startActivity(randomIntent)
            }
        }
    }

    private fun registerUserSecondExampleRetrofit( newRegisteredUser: RegisterDAO) {

        val retrofitService = RetrofitService().getRetrofit(applicationContext)

        GlobalScope.launch {

            try {

                /* val deferred1 = async { calculateHardThings(10, "11111") }
                val deferred2 = async { calculateHardThings(10, "22222") }

                val sum = deferred1.await() + deferred2.await() */

                val work2 = async { retrofitService.getPopular(newRegisteredUser)  }
                //val work2 = async { retrofitService.getPosts()  }

                val result2 =work2.await().awaitResponse()

                if (result2 != null) {

                    //println("SPLASHACTITIVTY ispis na drugom mjestu je: " + d1.get(1).term )
                }

                withContext(Dispatchers.Main) {

                    //var hashMapAppTerms = MyApplication.instance.items

                    //do something with result
                    val randomIntent = Intent(this@RegisterActivity, LoginActivity::class.java)
                    // Start the new activity.
                    startActivity(randomIntent)

                    finish()
                }

            } catch (exception: Exception) {
                exception.printStackTrace()
            }
        }

    }
}
