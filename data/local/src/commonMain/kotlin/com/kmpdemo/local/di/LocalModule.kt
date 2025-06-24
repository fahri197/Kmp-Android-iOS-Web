package com.kmpdemo.local.di

import com.kmpdemo.local.platformLocalModule
import com.kmpdemo.local.pref.KeyValueStorage
import com.kmpdemo.local.pref.KeyValueStorageImpl
import org.koin.dsl.module

val localModule = module {

    factory<KeyValueStorage> {
        KeyValueStorageImpl(
            settings = get()
        )
    }
}

val localModules = localModule + platformLocalModule
