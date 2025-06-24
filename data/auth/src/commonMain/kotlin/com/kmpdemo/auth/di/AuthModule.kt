package com.kmpdemo.auth.di

import com.kmpdemo.auth.api.FirebaseAuthService
import org.koin.dsl.module

val authModule = module {

//    single { FirebaseAuth.getInstance() }

    single { FirebaseAuthService() }
}