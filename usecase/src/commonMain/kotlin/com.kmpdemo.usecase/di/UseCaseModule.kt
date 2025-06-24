package com.kmpdemo.usecase.di

import com.kmpdemo.data.di.dataModules
import com.kmpdemo.usecase.auth.AuthUseCases
import com.kmpdemo.usecase.auth.MobileRegisteredUseCase
import com.kmpdemo.usecase.auth.MobileRegisteredUseCaseImpl
import com.kmpdemo.usecase.auth.RegisterUserUseCase
import com.kmpdemo.usecase.auth.RegisterUserUseCaseImpl
import com.kmpdemo.usecase.auth.SendOtpUseCase
import com.kmpdemo.usecase.auth.SendOtpUseCaseImpl
import com.kmpdemo.usecase.auth.VerifyOtpUseCase
import com.kmpdemo.usecase.auth.VerifyOtpUseCaseImpl
import com.kmpdemo.usecase.home.HomeFeedUseCase
import com.kmpdemo.usecase.home.HomeFeedUseCaseImpl
import com.kmpdemo.usecase.match.MatchRemoteUseCase
import com.kmpdemo.usecase.match.MatchRemoteUseCaseImpl
import org.koin.dsl.module

private val useCaseModule = module {

    factory<MatchRemoteUseCase> {
        MatchRemoteUseCaseImpl(matchRepository = get())
    }

    factory<MobileRegisteredUseCase> {
        MobileRegisteredUseCaseImpl(authRepository = get())
    }

    factory<SendOtpUseCase> {
        SendOtpUseCaseImpl(authRepository = get())
    }

    factory<VerifyOtpUseCase> {
        VerifyOtpUseCaseImpl(authRepository = get())
    }

    factory<RegisterUserUseCase> {
        RegisterUserUseCaseImpl(authRepository = get())
    }

    factory<HomeFeedUseCase>{
        HomeFeedUseCaseImpl(homeRepository = get())
    }

    factory<AuthUseCases> {
        AuthUseCases(
            sendOtpUseCase = get(),
            verifyOtpUseCase = get(),
            mobileRegisteredUseCase = get(),
            registerUserUseCase = get()
        )
    }
}

val useCaseModules = useCaseModule + dataModules