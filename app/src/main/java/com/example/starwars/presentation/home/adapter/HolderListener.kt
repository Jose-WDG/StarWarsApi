package com.example.starwars.presentation.home.adapter

interface HolderListener<T> {
    fun listenerImpl(position: Int?, lista: List<T>?)
}