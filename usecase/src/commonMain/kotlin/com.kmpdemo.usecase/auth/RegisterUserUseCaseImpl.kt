package com.kmpdemo.usecase.auth

import com.kmpdemo.domain.entities.auth.RegisterUserRequest
import com.kmpdemo.domain.entities.match.MatchResponse
import com.kmpdemo.domain.repositories.AuthRepository

internal class RegisterUserUseCaseImpl(
    private val authRepository: AuthRepository,
) : RegisterUserUseCase {

    override suspend fun execute(input: RegisterUserRequest): Result<MatchResponse> {
        return authRepository.registerUser(request = input)
    }

}