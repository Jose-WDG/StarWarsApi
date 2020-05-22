package com.example.starwars.infrastructure

import com.google.gson.annotations.SerializedName

class Results(@SerializedName("results")var resultados:List<Filme>)