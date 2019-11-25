package com.example.final_project.core

import android.app.Application
import com.example.final_project.api.inteceptors.SignInInterceptor
import com.example.final_project.api.interfaces.ApiInterface
import com.orhanobut.hawk.Hawk
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainApplication :
    Application() {
    private lateinit var retrofit: Retrofit
    private lateinit var api: ApiInterface
    override fun onCreate() {
        super.onCreate()

        Hawk.init(
            this
        )
            .build()
        val loggingInterceptor =
            HttpLoggingInterceptor()
        loggingInterceptor.level =
            HttpLoggingInterceptor.Level.BASIC
        val client =
            OkHttpClient.Builder()
                .addInterceptor(
                    SignInInterceptor()
                )
                .addInterceptor(
                    loggingInterceptor
                )
                .build()

        retrofit =
            Retrofit.Builder()
                .client(
                    client
                )
                .baseUrl(
                    "https://fudokosmarthome.azurewebsites.net/"
                )
                .addConverterFactory(
                    GsonConverterFactory.create()
                )
                .build()
        api =
            retrofit.create(
                ApiInterface::class.java
            )
    }

    fun getApi(): ApiInterface =
        api

}


