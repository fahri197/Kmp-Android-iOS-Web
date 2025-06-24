package com.kmpdemo.domain.repositories

import com.kmpdemo.domain.entities.auth.MobileRegisteredResponse
import com.kmpdemo.domain.entities.auth.RegisterUserRequest
import com.kmpdemo.domain.entities.match.MatchResponse

interface AuthRepository {
    suspend fun checkMobileRegistered(phoneNumber: String): Result<MobileRegisteredResponse>
    suspend fun sendOtp(phoneNumber: String): Result<MatchResponse>
    suspend fun verifyOtp(otp: String): Result<MatchResponse>
    suspend fun registerUser(request: RegisterUserRequest): Result<MatchResponse>
}