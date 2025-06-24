package com.kmpdemo.presentation.utils

sealed class AppRoute(val route: String) {
    data object Login : AppRoute("login")
    data object Otp : AppRoute("otp")
    data object Register : AppRoute("register")
    data object Location : AppRoute("location")
    data object Main : AppRoute("commonMain")
}