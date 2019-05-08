package com.example.perfectpitchcoach.practices

import com.example.perfectpitchcoach.App
import com.example.perfectpitchcoach.R
import com.google.gson.Gson

object PracticeEntity {

    fun getPracticeFromJsonRaw(id: Int): PracticeEntity.PracticeInfo? {
        val objectArrayString: String =
            App.ref.resources.openRawResource(R.raw.masterclass_00_03).bufferedReader().use { it.readText() }
        return Gson().fromJson(objectArrayString, PracticeEntity.PracticeInfo::class.java)
    }

    data class PracticeBatch (
        var id: Int = 0,
        var question: List<List<String>>? = null,
        var anwser: List<List<String>>? = null
    )

    data class PracticeInfo (
        var group: String? = null,
        var practice: String? = null,
        var description: String? = null,
        var uuid: String? = null,
        var maxHits: Int = 0,
        var practiceTyp: String? = null,
        var practiceBatch: List<PracticeBatch>? = null
    )
}