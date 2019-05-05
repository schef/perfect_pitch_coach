package com.example.perfectpitchcoach

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tvLog = findViewById<TextView>(R.id.tvLog)
        val etLog = findViewById<EditText>(R.id.etLog)
        val btnMidiToPitch = findViewById<Button>(R.id.btnMidiToPitch)
        val btnPitchToMidi = findViewById<Button>(R.id.btnPitchToMidi)

        btnMidiToPitch?.setOnClickListener {
            //TODO: is there a oneliner?
            var midiList = arrayListOf<Int>()
            val textList = etLog.text.split(" ")
            for (text in textList) {
                val number = text.toIntOrNull()
                if (number != null) {
                    midiList.add(number)
                }
            }
            val pitchList = PitchParser.getPitchListFromMidiList(midiList)
            val pitchString = pitchList.joinToString(" ")
            tvLog.setText(pitchString!!)
        }

        btnPitchToMidi?.setOnClickListener {
            var textList = etLog.text.split(" ")
            val midiList = PitchParser.getMidiListFromPitchList(textList)
            val midiString = midiList.joinToString(" ")
            tvLog.setText(midiString)
        }
    }


}