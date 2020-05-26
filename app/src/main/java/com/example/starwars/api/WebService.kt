package com.example.starwars.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WebService private constructor() {
    private var retrofit: Retrofit
    private var loggingInterceptor: HttpLoggingInterceptor
    private var httpClientBuilder: OkHttpClient.Builder
    private val URL = "https://swapi.dev/api/"

    companion object{
        private var instance:WebService? = null
        @Synchronized
        fun getInstance(): WebService? {
            if (instance == null){
                instance = WebService()
            }
            return instance
        }
    }

    init {
        loggingInterceptor = HttpLoggingInterceptor().setLevel(BODY)
        httpClientBuilder = OkHttpClient.Builder().addInterceptor(loggingInterceptor)
        retrofit = Retrofit.Builder()
            .baseUrl(URL)
            .client(httpClientBuilder.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun createService(): WebServoceApi = retrofit.create(WebServoceApi::class.java)
}