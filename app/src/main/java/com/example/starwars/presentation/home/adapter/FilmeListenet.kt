package com.example.starwars.presentation.home.adapter

interface FilmeListener<T> {
    fun listenerImpl(position: Int?, lista: List<T>?)
}