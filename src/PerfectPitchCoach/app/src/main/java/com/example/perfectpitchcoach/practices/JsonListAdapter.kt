package com.example.perfectpitchcoach.practices

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.perfectpitchcoach.App
import com.example.perfectpitchcoach.R
import kotlinx.android.synthetic.main.activity_json_list_item.view.*


class JsonListAdapter(val items: List<JsonListEntity.ListItem>?) :
    RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(App.ref).inflate(R.layout.activity_json_list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return items!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvItemName.text = items!!.get(position).name
        holder.tvItemPercent.text = 0.toString()
    }
}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val tvItemName = view.tvItemName
    val tvItemPercent = view.tvItemPercent
}
