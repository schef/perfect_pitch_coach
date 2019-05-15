package com.example.perfectpitchcoach.practices

import com.example.perfectpitchcoach.App
import com.google.gson.Gson

object JsonListEntity {
    fun getListFromJsonRaw(id: Int): JsonListEntity.ListInfo {
        val objectArrayString: String = App.ref.resources.openRawResource(id).bufferedReader().use { it.readText() }
        return Gson().fromJson(objectArrayString, JsonListEntity.ListInfo::class.java)
    }

    data class ListInfo (val description: String, val list: List<ListItem>)

    data class ListItem (val name: String, val filename: String)
}