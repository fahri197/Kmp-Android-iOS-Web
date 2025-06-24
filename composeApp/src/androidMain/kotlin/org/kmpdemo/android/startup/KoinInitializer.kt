package org.kmpdemo.android.startup

import android.content.Context
import androidx.startup.Initializer
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.kmpdemo.android.di.appModules

class KoinInitializer : Initializer<Unit> {

    override fun create(context: Context) {
        startKoin {
            androidContext(context)
            modules(appModules)
        }
    }

    override fun dependencies() = emptyList<Class<out Initializer<*>>>()
}