package com.kmpdemo.auth.di

import com.kmpdemo.auth.ui.viewmodel.AuthViewModel
import org.koin.dsl.module

val authenticationModules = module {

    single {
        AuthViewModel(
            authUseCases = get(),
            preferences = get()
        )
    }
}