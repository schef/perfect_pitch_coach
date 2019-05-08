package com.example.perfect_pitch_trainer.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



public class MasterClassMainData {

    @SerializedName("description")
    @Expose
    val description: String? = null
    @SerializedName("list")
    @Expose
    val list: MutableList<MasterClassList> = mutableListOf()



    /* fun getGroup(): String? {
        return group
    }

    fun setGroup(group: String) {
        this.group = group
    }

    fun getPractice(): String? {
        return practice
    }

    fun setPractice(practice: String) {
        this.practice = practice
    }

    fun getDescription(): String? {
        return description
    }

    fun setDescription(description: String) {
        this.description = description
    }

    fun getUuid(): String? {
        return uuid
    }

    fun setUuid(uuid: String) {
        this.uuid = uuid
    }

    fun getMaxHits(): Int? {
        return maxHits
    }

    fun setMaxHits(maxHits: Int?) {
        this.maxHits = maxHits
    }

    fun getPracticeTyp(): String? {
        return practiceTyp
    }

    fun setPracticeTyp(practiceTyp: String) {
        this.practiceTyp = practiceTyp
    }

    fun getPracticeBatch(): List<MasterClassList>? {
        return practiceBatch
    }

    fun setPracticeBatch(practiceBatch: List<MasterClassList>) {
        this.practiceBatch = practiceBatch
    } */
}