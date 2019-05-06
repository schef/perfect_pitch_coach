package com.example.perfectpitchcoach

import android.content.Context
import android.media.MediaPlayer
import android.util.Log
import java.util.*
import kotlin.concurrent.schedule

class MidiPlayer {

    companion object {
        val noteDuration = 1000L
        val firstTone = 21
        val lastTone = 108
        var players = arrayListOf<MediaPlayer>()


        fun getResNote(midi: Int): Int {
            return App.ref.resources.getIdentifier("piano_" + midi, "raw", App.ref.packageName)
        }

        init {
            for (x in firstTone..lastTone) {
                players.add(MediaPlayer.create(App.ref, getResNote(x)))
            }
        }

        fun noteOn(midi: Int) {
            Log.e("MidiPlayer", "noteOn: " + midi.toString())
            players[midi - firstTone].start()
        }

        fun noteOff(midi: Int) {
            Log.e("MidiPlayer", "noteOff: " + midi.toString())
            players[midi - firstTone].pause()
            players[midi - firstTone].seekTo(0)
        }

        fun playMultipleNotesMelodicly(pitchList: List<String>) {
            Log.e("MidiPlayer", pitchList.toString())
            val pitch = pitchList.get(0)
            val midi = PitchParser.getMidiFromPitch(pitch)
            noteOn(midi)
            Timer().schedule(noteDuration) {
                noteOff(midi)
                if (pitchList.lastIndex > 0) {
                    playMultipleNotesMelodicly(pitchList.subList(1, pitchList.lastIndex + 1))
                }
            }
        }

        fun playMultipleNotesHarmonicly(pitchList: List<String>){
            for (pitch in pitchList) {
                val midi = PitchParser.getMidiFromPitch(pitch)
                noteOn(midi)
                Timer().schedule(noteDuration) {
                    noteOff(midi)
                }
            }
        }
    }
}