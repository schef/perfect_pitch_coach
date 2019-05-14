package com.example.perfectpitchcoach.practices

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.perfectpitchcoach.R
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_json_list.*

//https://medium.com/@hinchman_amanda/working-with-recyclerview-in-android-kotlin-84a62aef94ec

class JsonListActivity : AppCompatActivity() {

    val mobileArray =
        arrayOf("Android", "IPhone", "WindowsMobile", "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.perfectpitchcoach.R.layout.activity_json_list)

        val jsonList = JsonListEntity.getListFromJsonRaw(R.raw.masterclasses)

//        Log.e("JsonListActivity", jsonList.toString())

        val adapter = ArrayAdapter(
            this,
            com.example.perfectpitchcoach.R.layout.activity_json_list_item, mobileArray
        )

        lvList.setAdapter(adapter)

        lvList.setOnItemClickListener { parent, view, position, id ->
            Log.e("JsonListActivity", jsonList!!.list!!.get(position).toString())
        }
    }
}