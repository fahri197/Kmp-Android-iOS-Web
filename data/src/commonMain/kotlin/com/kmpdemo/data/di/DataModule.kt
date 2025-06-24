package com.kmpdemo.data.di

import com.kmpdemo.auth.di.authModule
import com.kmpdemo.data.repositoriesImpl.AuthRepositoryImpl
import com.kmpdemo.data.repositoriesImpl.HomeRepositoryImpl
import com.kmpdemo.data.repositoriesImpl.MatchRepositoryImpl
import com.kmpdemo.domain.repositories.AuthRepository
import com.kmpdemo.domain.repositories.HomeRepository
import com.kmpdemo.domain.repositories.MatchesRepository
import com.kmpdemo.local.di.localModules
import com.kmpdemo.network.di.networkModule
import org.koin.core.qualifier.named
import org.koin.dsl.module

private val repositoriesModule = module {

    factory<MatchesRepository> {
        MatchRepositoryImpl(
            matchApiServices = get(),
            matchApiKey = get(named("match_api_key"))
        )
    }

    factory<AuthRepository> {
        AuthRepositoryImpl(
            firebaseAuthService = get()
        )
    }

    factory<HomeRepository> {
        HomeRepositoryImpl(
            homeApiServices = get()
        )
    }
}

val dataModules = repositoriesModule + networkModule + authModule + localModules