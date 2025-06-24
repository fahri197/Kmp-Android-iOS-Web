package com.kmpdemo.data.repositoriesImpl

import com.kmpdemo.auth.api.FirebaseAuthService
import com.kmpdemo.domain.entities.match.MatchResponse
import com.kmpdemo.domain.entities.auth.MobileRegisteredResponse
import com.kmpdemo.domain.entities.auth.RegisterUserRequest
import com.kmpdemo.domain.repositories.AuthRepository

internal class AuthRepositoryImpl(
    private val firebaseAuthService: FirebaseAuthService
) : AuthRepository {

    override suspend fun checkMobileRegistered(phoneNumber: String): Result<MobileRegisteredResponse> {
        return Result.runCatching {
            firebaseAuthService.checkMobileRegistered(phoneNumber)
        }
    }

    override suspend fun sendOtp(phoneNumber: String): Result<MatchResponse> {
        return Result.runCatching {
            firebaseAuthService.sendOtp(phoneNumber)
        }
    }

    override suspend fun verifyOtp(otp: String): Result<MatchResponse> {
        return Result.runCatching {
            firebaseAuthService.verifyOtp(otp)
        }
    }

    override suspend fun registerUser(request: RegisterUserRequest): Result<MatchResponse> {
        return Result.runCatching {
            firebaseAuthService.registerUser(request)
        }
    }

}