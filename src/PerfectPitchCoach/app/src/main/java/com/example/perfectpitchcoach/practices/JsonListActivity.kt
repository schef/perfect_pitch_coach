package com.example.perfectpitchcoach.practices

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.example.perfectpitchcoach.R
import com.example.perfectpitchcoach.practices.JsonListEntity.ListInfo
import kotlinx.android.synthetic.main.activity_json_list.*

//https://medium.com/@hinchman_amanda/working-with-recyclerview-in-android-kotlin-84a62aef94ec

class JsonListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.perfectpitchcoach.R.layout.activity_json_list)

        val jsonList = JsonListEntity.getListFromJsonRaw(R.raw.masterclasses)

        tvTitle.setText(jsonList?.description)

        Log.e("JsonListActivity", jsonList.toString())

        rvList.layoutManager = LinearLayoutManager(this)
        rvList.adapter = JsonListAdapter(jsonList?.list)

    }
}