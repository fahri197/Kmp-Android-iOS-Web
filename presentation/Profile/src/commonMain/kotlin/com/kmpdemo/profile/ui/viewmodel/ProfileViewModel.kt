package com.kmpdemo.profile.ui.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ProfileViewModel : ViewModel() {

    private val _text = MutableStateFlow("This is notifications Fragment")
    val text: StateFlow<String> = _text
}