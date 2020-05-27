package com.example.starwars.api

import com.example.starwars.infrastructure.Filme
import com.example.starwars.infrastructure.Results
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface WebServoceApi {

    @GET("films/")
    fun requestFilms(): Call<Results>

    @GET("films/{position}/")
    fun requestFilmsForPosition(@Path("position") position: String): Call<Filme>

}