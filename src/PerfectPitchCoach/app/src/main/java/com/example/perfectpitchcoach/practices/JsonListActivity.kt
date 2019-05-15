package com.example.perfectpitchcoach.practices

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import com.example.perfectpitchcoach.App
import com.example.perfectpitchcoach.R
import com.example.perfectpitchcoach.util.RecyclerItemClickListener
import kotlinx.android.synthetic.main.activity_json_list.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class JsonListActivity : AppCompatActivity() {

    private val adapter = JsonListAdapter()
    private fun loadData() = GlobalScope.launch(Dispatchers.Default) {
        val jsonList = JsonListEntity.getListFromJsonRaw(R.raw.masterclasses)
        withContext(Dispatchers.Main) {
            tvTitle.text = jsonList.description
            adapter.updateItems(jsonList.list)

            tvLoading.visibility = View.GONE
            layMain.visibility = View.VISIBLE
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.perfectpitchcoach.R.layout.activity_json_list)

        tvTitle.text = ""
        rvList.layoutManager = LinearLayoutManager(App.ref)
        rvList.adapter = adapter
        rvList.addOnItemTouchListener(
            RecyclerItemClickListener(this, rvList, object : RecyclerItemClickListener.OnItemClickListener {
                override fun onItemClick(view: View, position: Int) {
                    handleItemClick(position)
                }

                override fun onLongItemClick(view: View, position: Int) {
                    // do whatever
                }
            })
        )

        loadData()
    }

    private fun handleItemClick(position: Int) {
        val item = adapter.items[position]
        tvTitle.setText(item.filename)
        Log.e("JsonListActivity", position.toString())

    }
}