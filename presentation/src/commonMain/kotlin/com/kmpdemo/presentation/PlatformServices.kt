package com.kmpdemo.presentation

import kotlinx.coroutines.flow.SharedFlow

interface PlatformServices {
    val uiEvents: SharedFlow<Any>
    fun startListeningUiEvents(onEvent: (Any) -> Unit)

    fun showToast(message: String)
    fun showDialog(title: String, message: String)
    fun log(message: String)
    fun getDeviceLocale(): String
}
