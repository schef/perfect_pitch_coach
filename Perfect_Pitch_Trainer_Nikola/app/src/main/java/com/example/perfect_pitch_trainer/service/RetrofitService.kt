package com.example.brc.kotlintestapp.Settings

import android.content.Context
import com.example.perfect_pitch_trainer.model.CustomTrust
import com.example.perfect_pitch_trainer.service.SelfSigningClientBuilder
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitService {


    companion object {
        //private const val BASE_URL = "https://api.themoviedb.org/3/movie/"

        //private const val BASE_URL = "https://jsonplaceholder.typicode.com"
        private const val BASE_URL = "https://192.168.88.23:5000/"
    }


    /* fun getServiceUtil(retrofit: Retrofit): RetrofitEndpoints = retrofit.create(RetrofitEndpoints::class.java)

    fun getGson() = GsonBuilder().create()!! */

    val selfSigningClientBuilder: SelfSigningClientBuilder = SelfSigningClientBuilder()

    fun getRetrofit( context: Context ): RetrofitEndpoints {

        val gson = GsonBuilder()
                .setLenient()
                .create()

        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(selfSigningClientBuilder.createClient(context))
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory( GsonConverterFactory.create()  )
                //.client(makeOkHttpClient())
                .build().create(RetrofitEndpoints::class.java)
    }

    private fun makeOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(makeLoggingInterceptor())
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .writeTimeout(90, TimeUnit.SECONDS)
                .build()
    }

    private fun makeLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level =
                HttpLoggingInterceptor.Level.BODY
        return logging
    }



}