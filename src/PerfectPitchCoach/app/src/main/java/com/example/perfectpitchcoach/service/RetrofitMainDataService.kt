package com.example.perfect_pitch_trainer.service

import android.content.Context
import com.example.brc.kotlintestapp.Settings.EndpointsLoginRegister
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import com.google.gson.Gson



class RetrofitMainDataService {

    companion object {
        //private const val BASE_URL = "https://api.themoviedb.org/3/movie/"

        //private const val BASE_URL = "https://jsonplaceholder.typicode.com"

        // linkovi na kojima mi zeli sparsati json format:
        // 1) sa https => https://www.journaldev.com/13639/retrofit-android-example-tutorial
        // 2) sa http => http://www.androiddeft.com/2017/10/08/retrofit-android/

        private const val BASE_URL = "https://raw.githubusercontent.com/schef/ppt/master/practices/"
    }


    /* fun getServiceUtil(retrofit: Retrofit): EndpointsLoginRegister = retrofit.create(EndpointsLoginRegister::class.java)

    fun getGson() = GsonBuilder().create()!! */

    val selfSigningClientBuilder: SelfSigningClientBuilder = SelfSigningClientBuilder()

    fun getRetrofit( ): EndpointDataMasterClass {


        val gson = GsonBuilder()
            .setLenient()
            .create()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(makeOkHttpClient())
            //.client(selfSigningClientBuilder.createClient(context))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory( GsonConverterFactory.create(gson)  )
            //.client(makeOkHttpClient())
            .build().create(EndpointDataMasterClass::class.java)
    }

    private fun makeOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(makeLoggingInterceptor())
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .build()
    }

    private fun makeLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level =
            HttpLoggingInterceptor.Level.BODY
        return logging
    }


}