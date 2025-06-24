package com.kmpdemo.diy.ui.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DIYViewModel : ViewModel() {

    private val _text = MutableStateFlow("This is dashboard Fragment")
    val text: StateFlow<String> = _text

}