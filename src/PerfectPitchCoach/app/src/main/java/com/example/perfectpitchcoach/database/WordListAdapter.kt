package com.example.perfect_pitch_trainer.database

/*
 * Copyright (C) 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.perfect_pitch_trainer.database.model.MasterClass
import android.app.Activity
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import com.example.perfectpitchcoach.R


class WordListAdapter internal constructor(
        context: Context, userSolvedMasterClass: Int
) : RecyclerView.Adapter<WordListAdapter.WordViewHolder>() {


    private val userSolvedMasterClass = userSolvedMasterClass
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var masterClass = emptyList<MasterClass>() // Cached copy of words

    inner class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val wordItemView: TextView = itemView.findViewById(R.id.textView)
        val btSolveTask: Button = itemView.findViewById(R.id.btSolveTask)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val itemView = inflater.inflate(R.layout.room_database_recyclerview_item, parent, false)
        return WordViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val current = masterClass[position]
        holder.wordItemView.text = current.name + "  -  " + current.solved
        if( current.solved != "YES" ) {
            holder.btSolveTask.setBackgroundColor( ContextCompat.getColor(inflater.context, R.color.colorAccent) )
            holder.btSolveTask.setOnClickListener(object : View.OnClickListener {
                override fun onClick(v: View) {

                    val intent = Intent(inflater.context, UpdateDatabaseRoomActivity::class.java)
                    intent.putExtra("WholeMasterClassObject", current.id)
                    (inflater.context as Activity).startActivityForResult(intent, userSolvedMasterClass)
                }
            })
        }
        else {
            holder.btSolveTask.setBackgroundColor( ContextCompat.getColor(inflater.context, R.color.colorPrimary) )
        }
    }

    internal fun setWords(masterClass: List<MasterClass>) {
        this.masterClass = masterClass
        notifyDataSetChanged()
    }

    override fun getItemCount() = masterClass.size

}


