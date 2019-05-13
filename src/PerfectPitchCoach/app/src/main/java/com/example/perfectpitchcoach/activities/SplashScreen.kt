package com.example.perfectpitchcoach.activities

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import com.example.perfect_pitch_trainer.database.model.MasterClass
import com.example.perfect_pitch_trainer.database.repository.MasterClassRepository
import com.example.perfect_pitch_trainer.database.viewModels.MasterClassViewModel
import com.example.perfect_pitch_trainer.service.RetrofitMainDataService
import com.example.perfect_pitch_trainer.settings.AppPreferences
import com.example.perfectpitchcoach.R
import kotlinx.coroutines.*
import android.arch.lifecycle.Observer

class SplashScreen : AppCompatActivity() {

    lateinit var progressBar: ProgressBar
    private val SPLASH_DISPLAY_LENGTH = 1500

    private lateinit var masterClassViewModel: MasterClassViewModel
    private lateinit var masterClassRepository: MasterClassRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        progressBar = findViewById(R.id.progressBar)

        masterClassViewModel = ViewModelProviders.of(this).get(MasterClassViewModel::class.java)

        val mainIntent = Intent(this@SplashScreen, MainActivity::class.java)
        startActivity(mainIntent)
        finish()
        
        /* if (AppPreferences.firstRun == false) {
            //Log.d("Thrre Fragment", "The value of our pref is: ${AppPreferences.firstRun}")

            val mainIntent = Intent(this@SplashScreen, IntroductionActivity::class.java)
            startActivity(mainIntent)
            finish()
        } else {

            GlobalScope.launch {

                try {

                    //delay(1000)
                    val curr = System.currentTimeMillis()

                    val retrofitService = RetrofitMainDataService().getRetrofit()

                    val work2 = async { retrofitService.getMasterClassData()  }

                    val result2 =work2.await().await()

                    if (result2 != null) {

                        masterClassViewModel.allMasterClasses.observe( this@SplashScreen, Observer { masterClasses ->

                            if( masterClasses?.size == 0 ) {
                                for (webJsonItems in result2.body()?.list!!) {

                                    var masterClass: MasterClass = MasterClass("", "")
                                    masterClass.name = webJsonItems.name
                                    masterClass.solved = "NO"
                                    masterClassViewModel.insert(masterClass)
                                }
                            }
                            else {
                                // Update the cached copy of the words in the adapter.
                                for (webJsonItems in result2.body()?.list!!) {

                                    var masterClassNotExists: Boolean = false

                                    var masterClass: MasterClass = MasterClass("", "")
                                    if (masterClasses != null) {
                                        for (roomDatabaseItems in masterClasses) {

                                            if (webJsonItems.name == roomDatabaseItems.name) {

                                                masterClassNotExists = false
                                                break

                                            } else {
                                                masterClassNotExists = true
                                                masterClass.name = webJsonItems.name
                                                masterClass.solved = "NO"
                                            }
                                        }
                                    }

                                    if (masterClassNotExists == true) {
                                        masterClassViewModel.insert(masterClass)
                                    }
                                }
                            }
                        })


                        println("SPLASHACTITIVTY ispis na drugom mjestu je: " + result2 )
                        println("SPLASHACTITIVTY ispis na drugom mjestu je: " + result2 )
                        println("SPLASHACTITIVTY ispis na drugom mjestu je: " + result2 )
                        println("SPLASHACTITIVTY ispis na drugom mjestu je: " + result2 )
                    }

                    withContext(Dispatchers.Main) {

                        progressBar.visibility = View.GONE

                        if (AppPreferences.userLoggedIn == true) {
                            val mainIntent = Intent(this@SplashScreen, MainActivity::class.java)
                            startActivity(mainIntent)
                            finish()
                        } else {
                            val mainIntent = Intent(this@SplashScreen, LoginActivity::class.java)
                            startActivity(mainIntent)
                            finish()
                        }

                    }

                } catch (exception: Exception) {

                    withContext(Dispatchers.Main) {

                        progressBar.visibility = View.GONE
                    }

                    exception.printStackTrace()
                    exception.printStackTrace()
                }
            }
        } */
    }


}
