package com.example.brc.kotlintestapp.Settings

import com.example.perfect_pitch_trainer.model.Post
import com.example.perfect_pitch_trainer.model.RegisterDAO
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.POST

interface RetrofitEndpoints {

    //@HTTP(method = "POST", path = "signUpUser", hasBody = true)
    @POST("signUpUser")
    fun getPopular(@Body deviceInfo: RegisterDAO) : Call<Response<RegisterDAO>>

    //@GET("/posts")
    //fun getPosts(): Deferred<Response<List<Post>>>

    //@POST(ENDPOINT_PREFIX + "device/register")
   // fun registerDevice(@Body deviceInfo: RegisterDAO): Call<RegisterDAO>
}