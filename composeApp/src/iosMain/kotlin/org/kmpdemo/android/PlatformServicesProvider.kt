package org.kmpdemo.android

import com.kmpdemo.presentation.PlatformServices
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

object PlatformServicesProvider : KoinComponent {
    fun getPlatformServices(): PlatformServices = get()
}