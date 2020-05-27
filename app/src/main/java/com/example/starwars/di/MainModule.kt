package com.example.starwars.di

import com.example.starwars.presentation.home.HomeViewModel
import com.example.starwars.presentation.home.adapter.FilmesAdapter
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    viewModel {
        HomeViewModel()
    }

    factory {
        FilmesAdapter(listOf())
    }
}