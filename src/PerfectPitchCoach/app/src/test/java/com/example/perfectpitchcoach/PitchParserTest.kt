package com.example.perfectpitchcoach

import org.junit.Assert
import org.junit.Test
import java.util.Arrays.asList



class PitchParserTest {
    @Test
    fun isMidiPitch() {
        Assert.assertEquals(true, PitchParser.isMidiPitch(60, "c"))
        Assert.assertEquals(true, PitchParser.isMidiPitch(60, "c'''"))
        Assert.assertEquals(true, PitchParser.isMidiPitch(60, "c,,,"))
        Assert.assertEquals(false, PitchParser.isMidiPitch(60, "cis"))
    }

    @Test
    fun getMidiOctaveFromPitch() {
        Assert.assertEquals(PitchParser.midiBase, PitchParser.getMidiOctaveFromPitch("c"))
        Assert.assertEquals(PitchParser.midiBase + 12 * 3, PitchParser.getMidiOctaveFromPitch("cis'''"))
        Assert.assertEquals(PitchParser.midiBase - 12 * 2, PitchParser.getMidiOctaveFromPitch("g,,"))
    }

    @Test
    fun getMidiFromPitch() {
        Assert.assertEquals(60, PitchParser.getMidiFromPitch("c'"))
        Assert.assertEquals(59, PitchParser.getMidiFromPitch("h"))
        Assert.assertEquals((61 + 12), PitchParser.getMidiFromPitch("cis''"))
    }

    @Test
    fun getPitchOctaveFromMidi() {
        Assert.assertEquals("'", PitchParser.getPitchOctaveFromMidi(60))
        Assert.assertEquals("", PitchParser.getPitchOctaveFromMidi(59))
        Assert.assertEquals("''", PitchParser.getPitchOctaveFromMidi(72))
    }

    @Test
    fun getPitchFromMidi() {
        Assert.assertEquals("c'", PitchParser.getPitchFromMidi(60))
        Assert.assertEquals("h,", PitchParser.getPitchFromMidi(60 - 13))
        Assert.assertEquals("cis''", PitchParser.getPitchFromMidi(60 + 13))
    }

    @Test
    fun getMidiListFromPitchList() {
        Assert.assertTrue(listOf(60, 62, 64).equals(PitchParser.getMidiListFromPitchList(listOf("c'", "d'", "e'"))))
    }

    @Test
    fun getMidiBaseFromPitch() {
        Assert.assertEquals(0, PitchParser.getMidiBaseFromPitch("c''"))
        Assert.assertEquals(11, PitchParser.getMidiBaseFromPitch("h"))
    }
}