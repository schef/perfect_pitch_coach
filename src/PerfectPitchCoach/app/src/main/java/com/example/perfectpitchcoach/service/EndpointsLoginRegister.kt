package com.example.brc.kotlintestapp.Settings

import com.example.perfect_pitch_trainer.model.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface EndpointsLoginRegister {

    //@HTTP(method = "POST", path = "signUpUser", hasBody = true)
    @POST("signUpUser")
    fun getPopular(@Body deviceInfo: RegisterRequest) : Call<RegisterResponse>

    @POST("loginUser")
    fun getLoginUserData(@Body deviceInfo: LoginRequest) : Call<LoginResponse>

    @POST("passwordRecovery")
    fun getPasswordRecoveryData(@Body deviceInfo: PasswordRecoveryRequest) : Call<PasswordRecoveryResponse>

    @POST("passwordUpdate")
    fun getPasswordUpdateData(@Body deviceInfo: PasswordUpdateRequest) : Call<PasswordUpdateResponse>

    //@GET("/posts")
    //fun getPosts(): Deferred<Response<List<Post>>>

    //@POST(ENDPOINT_PREFIX + "device/register")
   // fun registerDevice(@Body deviceInfo: RegisterRequest): Call<RegisterRequest>
}