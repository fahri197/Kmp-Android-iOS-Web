package com.kmpdemo.profile.di

import com.kmpdemo.profile.ui.viewmodel.ProfileViewModel
import org.koin.dsl.module

val profileModules = module {

    single {
        ProfileViewModel()
    }
}