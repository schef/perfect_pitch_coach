package com.example.perfectpitchcoach

import kotlin.math.abs

class PitchParser {
    companion object {

        const val midiBase = 48

        val pitchMidiBase = mapOf(
            "c" to 0,
            "cis" to 1,
            "d" to 2,
            "dis" to 3,
            "e" to 4,
            "f" to 5,
            "fis" to 6,
            "g" to 7,
            "gis" to 8,
            "a" to 9,
            "ais" to 10,
            "h" to 11,
            "des" to 1,
            "es" to 3,
            "ges" to 6,
            "as" to 8,
            "b" to 10
        )

        fun getMidiBaseFromMidi(midi: Int): Int {
            return (midi % 12)
        }

        fun getPitchBase(pitch: String): String {
            val regex = Regex("[',]")
            return regex.replace(pitch, "")
        }

        fun isMidiPitch(midi: Int, pitch: String): Boolean {
            return getMidiBaseFromMidi(midi) == pitchMidiBase[getPitchBase(pitch)]
        }

        fun getMidiOctaveFromPitch(pitch: String): Int {
            var octave = 0
            for (char in pitch) {
                if (char.equals('\'')) {
                    octave++
                } else if (char.equals(',')) {
                    octave--
                }
            }
            return midiBase + octave * 12
        }

        fun getMidiFromPitch(pitch: String): Int {
            val octave = getMidiOctaveFromPitch(pitch)
            val pitchBase = getPitchBase(pitch)
            return (pitchMidiBase[pitchBase]!! + octave)
        }

        fun getPitchOctaveFromMidi(midi: Int): String {
            val rest = getMidiBaseFromMidi(midi)
            val center = midiBase / 12
            val octave = (midi - rest) / 12
            val centerOctave = octave - center
            if (centerOctave > 0) {
                return "'".repeat(centerOctave)
            } else if (centerOctave < 0) {
                return ",".repeat(abs(centerOctave))
            } else {
                return ""
            }
        }

        fun getPitchFromMidi(midi: Int): String {
            val octave = getPitchOctaveFromMidi(midi)
            for (pair in pitchMidiBase) {
                if (pair.value == getMidiBaseFromMidi(midi)) {
                    return pair.key + octave
                }
            }
            return ""
        }

        fun getMidiBaseFromPitch(pitch: String): Int {
            val midi = getMidiFromPitch(pitch)
            return getMidiBaseFromMidi(midi)
        }

        fun getMidiListFromPitchList(pitchList: List<String>): List<Int> {
            var midiList = arrayListOf<Int>()
            for (pitch in pitchList) {
                midiList.add(getMidiFromPitch(pitch))
            }
            return midiList
        }

        fun getPitchListFromMidiList(midiList: List<Int>): List<String> {
            var pitchList = arrayListOf<String>()
            for (midi in midiList) {
                pitchList.add(getPitchFromMidi(midi))
            }
            return pitchList
        }

        //TODO:
        fun isPitchValid(){

        }

        fun isMidiValid(){

        }

    }
}

