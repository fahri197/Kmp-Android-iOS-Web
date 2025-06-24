package org.kmpdemo.android.startup

import org.koin.core.context.startKoin
import org.kmpdemo.android.di.appModules

fun initKoin() {
    startKoin {
        modules(appModules)
    }
}

