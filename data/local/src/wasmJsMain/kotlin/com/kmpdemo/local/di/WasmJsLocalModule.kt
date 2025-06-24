package com.kmpdemo.local.di

import com.russhwolf.settings.Settings
import com.kmpdemo.local.WasmLocalStorageSettings
import org.koin.dsl.module

val wasmJsLocalModule = module {

    single<Settings> {
        WasmLocalStorageSettings()
    }

}