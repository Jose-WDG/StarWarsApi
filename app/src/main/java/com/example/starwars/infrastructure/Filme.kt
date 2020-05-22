package com.example.starwars.infrastructure

import com.google.gson.annotations.SerializedName

class Filme(var title:String,@SerializedName("release_date") var releaseDate:String)