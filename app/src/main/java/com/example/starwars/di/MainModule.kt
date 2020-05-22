package com.example.starwars.di

import com.example.starwars.presentation.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    viewModel {
        HomeViewModel()
    }

}