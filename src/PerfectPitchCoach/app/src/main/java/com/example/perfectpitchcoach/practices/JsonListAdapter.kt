package com.example.perfectpitchcoach.practices

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.perfectpitchcoach.App
import com.example.perfectpitchcoach.R
import kotlinx.android.synthetic.main.activity_json_list_item.view.*

class JsonListAdapter : RecyclerView.Adapter<JsonListAdapter.ViewHolder>() {
    var items = listOf<JsonListEntity.ListItem>()
        private set

    fun updateItems(items: List<JsonListEntity.ListItem>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(App.ref).inflate(R.layout.activity_json_list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvItemName = view.tvItemName
        private val tvItemPercent = view.tvItemPercent

        fun bind(item: JsonListEntity.ListItem) {
            tvItemName.text = item.name
            tvItemPercent.text = 0.toString()
        }
    }
}