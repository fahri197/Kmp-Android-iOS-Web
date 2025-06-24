package com.kmpdemo.local.di

import android.content.Context
import android.content.SharedPreferences
import com.russhwolf.settings.Settings
import com.russhwolf.settings.SharedPreferencesSettings
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val androidLocalModule = module {

    single<SharedPreferences> {
        androidContext().getSharedPreferences("kmpdemo_prefs", Context.MODE_PRIVATE)
    }

    single<Settings> {
        SharedPreferencesSettings(delegate = get())
    }

}