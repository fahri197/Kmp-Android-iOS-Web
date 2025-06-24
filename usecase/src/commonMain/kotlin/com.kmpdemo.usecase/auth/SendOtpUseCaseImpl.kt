package com.kmpdemo.usecase.auth

import com.kmpdemo.domain.entities.match.MatchResponse
import com.kmpdemo.domain.repositories.AuthRepository

internal class SendOtpUseCaseImpl(
    private val authRepository: AuthRepository,
) : SendOtpUseCase {

    override suspend fun execute(input: String): Result<MatchResponse> {
        return authRepository.sendOtp(phoneNumber = input)
    }

}