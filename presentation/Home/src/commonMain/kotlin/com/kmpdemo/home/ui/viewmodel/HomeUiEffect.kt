package com.kmpdemo.home.ui.viewmodel

import com.kmpdemo.presentation.utils.AppRoute

sealed interface HomeUiEffect {
    data class NavigateToScreen(val route: AppRoute) : HomeUiEffect
    data class ShowToast(val message: String) : HomeUiEffect
}