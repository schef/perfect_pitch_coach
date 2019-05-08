package com.example.perfectpitchcoach.practices

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.TextView
import com.example.perfectpitchcoach.R
import com.example.perfectpitchcoach.audio.MidiPlayer
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_practice_meditation.*
import java.io.IOException
import java.io.InputStream

class PracticeMeditationActivity : AppCompatActivity() {

    var score: Int = 0
    var batch: PracticeEntity.PracticeBatch? = null
    var practice: PracticeEntity.PracticeInfo? = null

    fun init() {
        practice = PracticeEntity.getPracticeFromJsonRaw(R.raw.masterclass_00_03)
        tvScoreMax.text = practice?.maxHits.toString()
        tvDescription.text = practice?.description
        Log.e("PracticeMeditation", practice.toString())
    }

    fun shuffle() {
        batch = practice?.practiceBatch?.random()
        tvPitch.text = batch?.question.toString()
        tvId.text = batch?.id.toString()
        tvScore.text = score.toString()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practice_meditation)

        init()
        shuffle()

        btnPlay.setOnClickListener {
            for (a in batch?.anwser!!) {
                MidiPlayer.playMultipleNotesMelodicly(a)
            }
        }

        btnNext.setOnClickListener {
            score++
            shuffle()
        }

        btnPlayC?.setOnClickListener {
            MidiPlayer.playMultipleNotesMelodicly(listOf("c'"))
        }

        btnSkip?.setOnClickListener {
            score = 0
            shuffle()
        }
    }
}