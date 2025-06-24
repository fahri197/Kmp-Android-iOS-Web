package com.kmpdemo.usecase.auth

import com.kmpdemo.domain.entities.auth.MobileRegisteredResponse
import com.kmpdemo.domain.repositories.AuthRepository

internal class MobileRegisteredUseCaseImpl(
    private val authRepository: AuthRepository,
) : MobileRegisteredUseCase {

    override suspend fun execute(input: String): Result<MobileRegisteredResponse> {
        return authRepository.checkMobileRegistered(phoneNumber = input)
    }

}