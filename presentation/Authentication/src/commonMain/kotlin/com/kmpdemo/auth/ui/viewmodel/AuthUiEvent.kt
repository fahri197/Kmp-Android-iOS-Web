package com.kmpdemo.auth.ui.viewmodel

sealed interface AuthUiEvent  {
    data class MobileChanged(val mobile: String) : AuthUiEvent
    data class NameChanged(val name: String) : AuthUiEvent
    data class EmailChanged(val email: String) : AuthUiEvent
    data class OtpChanged(val otp: String) : AuthUiEvent
    data object MobileRegistration : AuthUiEvent
    data object RequestOtp : AuthUiEvent
    data object ResendOtp : AuthUiEvent
    data object VerifyOtp: AuthUiEvent
    data object Register: AuthUiEvent
}