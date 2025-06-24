package com.kmpdemo.presentation

interface PlatformUiEvent{
    data class ShowToast(val message: String) : PlatformUiEvent
    data class ShowDialog(val title: String, val message: String) : PlatformUiEvent
}