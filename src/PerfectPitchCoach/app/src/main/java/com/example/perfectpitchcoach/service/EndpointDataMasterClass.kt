package com.example.perfect_pitch_trainer.service

import com.example.perfect_pitch_trainer.model.MasterClassMainData
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface EndpointDataMasterClass {

    @GET(value = "masterclass_00.json")
    fun getMasterClassData() : Deferred<Response<MasterClassMainData>>
}