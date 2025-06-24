package com.kmpdemo.auth.ui.viewmodel

import com.kmpdemo.presentation.utils.AppRoute

sealed interface AuthUiEffect {
    data class NavigateToScreen(val route: AppRoute) : AuthUiEffect
    data class ShowToast(val message: String) : AuthUiEffect
}