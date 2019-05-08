package com.example.perfectpitchcoach.database

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import com.example.perfect_pitch_trainer.database.NewWordActivity
import com.example.perfect_pitch_trainer.database.UpdateDatabaseRoomActivity
import com.example.perfect_pitch_trainer.database.WordListAdapter
import com.example.perfect_pitch_trainer.database.model.MasterClass
import com.example.perfect_pitch_trainer.database.viewModels.MasterClassViewModel
import com.example.perfectpitchcoach.R
import com.example.perfectpitchcoach.activities.MainActivity

class RoomDatabaseExampleActivity : AppCompatActivity() {

    private val newWordActivityRequestCode = 1
    private val userSolvedMasterClass = 2
    private lateinit var masterClassViewModel: MasterClassViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_database_example)

        //val toolbar = findViewById<Toolbar>(R.id.toolbar)
        //setSupportActionBar(toolbar)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = WordListAdapter(this, userSolvedMasterClass)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Get a new or existing ViewModel from the ViewModelProvider.
        masterClassViewModel = ViewModelProviders.of(this).get(MasterClassViewModel::class.java)

        // Add an observer on the LiveData returned by getAlphabetizedWords.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        masterClassViewModel.allMasterClasses.observe(this, Observer { words ->
            // Update the cached copy of the words in the adapter.
            words?.let { adapter.setWords(it) }
        })

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@RoomDatabaseExampleActivity, NewWordActivity::class.java)
            startActivityForResult(intent, newWordActivityRequestCode)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)

        if (requestCode == newWordActivityRequestCode && resultCode == Activity.RESULT_OK) {
            intentData?.let { data ->
                val masterClass =
                    MasterClass( data.getStringExtra(NewWordActivity.EXTRA_REPLY_NAME),
                        data.getStringExtra(NewWordActivity.EXTRA_REPLY_STATUS))
                masterClassViewModel.insert(masterClass)
            }
        }
        else if(  requestCode == userSolvedMasterClass && resultCode == Activity.RESULT_OK ) {

            println("Da li ce tu uci "  )
            println("Da li ce tu uci "  )
            println("Da li ce tu uci "  )

            var masterClass: MasterClass = MasterClass()

            intentData?.let { data ->

                val userId = data.getStringExtra(UpdateDatabaseRoomActivity.EXTRA_REPLY_USER_ID)

                masterClassViewModel.update(userId, "YES")

                /* masterClassViewModel.allMasterClasses.observe(this, Observer { masterClasses ->

                    if (masterClasses != null) {
                        for (roomDatabaseItems in masterClasses) {

                            if ( userId == roomDatabaseItems.id) {

                                masterClass = roomDatabaseItems
                                break
                            }
                        }
                    }

                }) */

                /* val masterClass =
                    MasterClass( data.getStringExtra(NewWordActivity.EXTRA_REPLY_NAME),
                        data.getStringExtra(NewWordActivity.EXTRA_REPLY_STATUS))
                masterClassViewModel.insert(masterClass) */


            }
        }
        else {
            Toast.makeText(
                applicationContext,
                "Something went wrong with saving data. Please try again later",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()

        val randomIntent = Intent (this@RoomDatabaseExampleActivity, MainActivity::class.java)
        finish()
        startActivity(randomIntent)
    }
}
