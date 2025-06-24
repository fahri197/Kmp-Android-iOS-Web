package org.kmpdemo.android

sealed class PlatformUiActions{
    data class ShowToast(val message: String) : PlatformUiActions()
    data class ShowDialog(val title: String, val message: String) : PlatformUiActions()
}