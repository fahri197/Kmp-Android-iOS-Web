package com.kmpdemo.presentation.di

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

@Composable
inline fun <reified T : Any> rememberKoinInstance(): T {
    return remember {
        object : KoinComponent {}.get<T>()
    }
}