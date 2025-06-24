package com.kmpdemo.home.di

import com.kmpdemo.home.ui.viewmodel.HomeViewModel
import org.koin.dsl.module

val homeModules = module {

    single {
        HomeViewModel(
            homeFeedUseCase = get(),
            preferences = get()
        )
    }
}