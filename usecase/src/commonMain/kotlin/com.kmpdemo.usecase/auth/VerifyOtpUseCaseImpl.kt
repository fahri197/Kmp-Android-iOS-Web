package com.kmpdemo.usecase.auth

import com.kmpdemo.domain.entities.match.MatchResponse
import com.kmpdemo.domain.repositories.AuthRepository

internal class VerifyOtpUseCaseImpl(
    private val authRepository: AuthRepository,
) : VerifyOtpUseCase {

    override suspend fun execute(input: String): Result<MatchResponse> {
        return authRepository.verifyOtp(otp = input)
    }

}