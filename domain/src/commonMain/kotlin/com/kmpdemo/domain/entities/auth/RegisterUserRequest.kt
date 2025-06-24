package com.kmpdemo.domain.entities.auth

data class RegisterUserRequest(
    val name: String,
    val email: String,
    val mobile: String
)