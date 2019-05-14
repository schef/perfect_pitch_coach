package com.example.perfectpitchcoach.practices

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.example.perfectpitchcoach.R
import kotlinx.android.synthetic.main.activity_json_list.*
import android.support.v7.widget.RecyclerView
import com.example.perfectpitchcoach.App
import kotlinx.android.synthetic.main.activity_json_list.view.*

class JsonListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.perfectpitchcoach.R.layout.activity_json_list)

        val jsonList = JsonListEntity.getListFromJsonRaw(R.raw.masterclasses)

        tvTitle.setText(jsonList?.description)

        tvTitle.setOnClickListener {
            Log.e("JsonListActivity", "Kaj ima")
        }

        Log.e("JsonListActivity", jsonList.toString())

        rvList.layoutManager = LinearLayoutManager(App.ref)
        rvList.adapter = JsonListAdapter(jsonList?.list)

        rvList.setOnClickListener {
            val viewHolder = it.getTag() as RecyclerView.ViewHolder
            val position = viewHolder.adapterPosition
            Log.e("JsonListActivity", "Kaj ima: " + jsonList?.list?.get(position)?.filename.toString())
        }
    }
}