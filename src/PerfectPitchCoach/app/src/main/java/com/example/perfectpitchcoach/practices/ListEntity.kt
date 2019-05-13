package com.example.perfectpitchcoach.practices

import com.example.perfectpitchcoach.App
import com.example.perfectpitchcoach.R
import com.google.gson.Gson

object ListEntity {

    fun getListFromJsonRaw(id: Int): ListEntity.ListInfo? {
        val objectArrayString: String =
            App.ref.resources.openRawResource(id).bufferedReader().use { it.readText() }
        return Gson().fromJson(objectArrayString, ListEntity.ListInfo::class.java)
    }

    data class ListInfo (
        var description: String? = null,
        var list: List<ListItem>? = null
    )

    data class ListItem (
        var name: String? = null,
        var filename: String? = null
        )
}