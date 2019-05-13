package com.example.perfectpitchcoach.practices

import android.R.attr.name
import android.R
import android.content.Context
import android.widget.TextView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.R.attr.name
import kotlinx.android.synthetic.main.activity_json_list_item.*
import kotlinx.android.synthetic.main.activity_json_list.*


class JsonListAdapter(context: Context, items: ArrayList<ListEntity>) : ArrayAdapter<ListEntity>(context, 0, items) {

    @Override
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        var convertView = convertView

        // Get the data item for this position

        val item = getItem(position)

        // Check if an existing view is being reused, otherwise inflate the view

        if (convertView == null) {

            convertView = LayoutInflater.from(context).inflate(R.layout.activity_list_item, parent, false)

        }

        // Return the completed view to render on screen

        return convertView

    }

}