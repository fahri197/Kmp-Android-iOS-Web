package com.kmpdemo.auth.api

import com.kmpdemo.domain.entities.match.MatchResponse
import com.kmpdemo.domain.entities.auth.MobileRegisteredResponse
import com.kmpdemo.domain.entities.auth.RegisterUserRequest

class FirebaseAuthService(/*private val firebaseAuth: FirebaseAuth*/) {

    suspend fun checkMobileRegistered(phoneNumber: String): MobileRegisteredResponse {
        // Here you should trigger OTP sending, simplified for now
        //firebaseAuth.signInWithPhoneNumber(phoneNumber) // Pseudo-method; real will be via callback
        return MobileRegisteredResponse(isRegistered = false);
    }

    suspend fun sendOtp(phoneNumber: String): MatchResponse {
        // Here you should trigger OTP sending, simplified for now
        //firebaseAuth.signInWithPhoneNumber(phoneNumber) // Pseudo-method; real will be via callback
        return MatchResponse();
    }

    suspend fun verifyOtp(otp: String): MatchResponse {
        // Here you should trigger OTP sending, simplified for now
        //firebaseAuth.signInWithPhoneNumber(phoneNumber) // Pseudo-method; real will be via callback
        return MatchResponse();
    }

    suspend fun signInWithCredential(credential: Any) {
        //firebaseAuth.signInWithCredential(credential).await()
    }

    suspend fun registerUser(request: RegisterUserRequest): MatchResponse {
        return MatchResponse();
    }

    //fun getCurrentUser() = firebaseAuth.currentUser
}