package com.example.starwars.api

import com.example.starwars.infrastructure.Results
import retrofit2.Call
import retrofit2.http.GET

interface WebServoceApi {

    @GET("films/")
    fun requestFilms(): Call<Results>

}