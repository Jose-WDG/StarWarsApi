package com.example.starwars.infrastructure

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Filme(var title:String,@SerializedName("release_date") var releaseDate:String) :Serializable