package com.kmpdemo.home.ui.viewmodel

sealed class HomeUiEvent {
    object LoadHomeScreen : HomeUiEvent()
    object LoadMoreServices : HomeUiEvent()
}