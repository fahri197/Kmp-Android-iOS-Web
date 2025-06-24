package com.kmpdemo.auth.ui.viewmodel

data class AuthUiState(
    val mobile: String = "",
    val email: String = "",
    val name: String = "",
    val otp: String = "",
    val isLoading: Boolean = false,
    val isOtpSent: Boolean = false,
    val isNewUser: Boolean = false
){
    val isValidMobile: Boolean
        get() = mobile.length == 10 && mobile.all { it.isDigit() }

    val isValidEmail: Boolean
        get() = email.isNotBlank() && email.matches(Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"))

    val isValidOTP: Boolean
        get() = otp.length == 4  && otp.all { it.isDigit() }

    val isValidName: Boolean
        get() {
            val parts = name.trim().split("\\s+".toRegex())
            val validWords = parts.filter { it.length >= 2 && it.matches("[A-Za-z\\u0900-\\u097F'.-]+".toRegex()) }
            return validWords.size >= 2
        }
}