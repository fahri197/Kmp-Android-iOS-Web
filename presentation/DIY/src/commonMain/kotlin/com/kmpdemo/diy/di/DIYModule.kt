package com.kmpdemo.diy.di

import com.kmpdemo.diy.ui.viewmodel.DIYViewModel
import org.koin.dsl.module

val diyModules = module {

    single {
        DIYViewModel()
    }
}