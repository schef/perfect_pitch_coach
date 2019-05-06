package com.example.perfectpitchcoach

import android.media.AudioAttributes
import android.media.SoundPool
import android.media.audiofx.LoudnessEnhancer
import android.media.audiofx.PresetReverb
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.Log
import android.widget.Toast
import java.util.*
import kotlin.concurrent.schedule

class MidiPlayer {

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    companion object {
        val noteDuration = 1000L
        val firstTone = 21
        val lastTone = 108
        var streamId = mutableMapOf<Int, Int>()

        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        var attributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_MEDIA)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build()

        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        val soundPool = SoundPool.Builder()
            .setAudioAttributes(attributes)
            .setMaxStreams(5)
            .build();

        fun getResNote(midi: Int): Int {
            return App.ref.resources.getIdentifier("piano_" + midi, "raw", App.ref.packageName)
        }

        init {
            for (x in firstTone..lastTone) {
                soundPool.load(App.ref, getResNote(x), 1)
            }
            soundPool.setOnLoadCompleteListener(SoundPool.OnLoadCompleteListener { soundPool, sampleId, status ->
//                Toast.makeText(
//                    App.ref,
//                    "Sound load finished",
//                    Toast.LENGTH_SHORT
//                ).show()
                Log.e("MidiPlayer", soundPool.toString() + sampleId.toString() + status.toString())
            })
        }

        fun noteOn(midi: Int) {
            Log.e("MidiPlayer", "noteOn: " + midi.toString())
            val id = soundPool.play(midi - firstTone, 1F, 1F, 1, 0, 1F)
            streamId.put(midi, id)
        }

        fun noteOff(midi: Int) {
            Log.e("MidiPlayer", "noteOff: " + midi.toString())
            soundPool.stop(streamId[midi]!!)
            streamId.remove(midi)
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

        fun playMultipleNotesHarmonicly(pitchList: List<String>) {
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