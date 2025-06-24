package com.kmpdemo.usecase.auth

data class AuthUseCases(
    val mobileRegisteredUseCase: MobileRegisteredUseCase,
    val sendOtpUseCase: SendOtpUseCase,
    val verifyOtpUseCase: VerifyOtpUseCase,
    val registerUserUseCase: RegisterUserUseCase
)
