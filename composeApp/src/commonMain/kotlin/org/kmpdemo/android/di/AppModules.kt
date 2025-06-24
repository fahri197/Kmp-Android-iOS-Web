package org.kmpdemo.android.di

import com.kmpdemo.auth.di.authenticationModules
import com.kmpdemo.diy.di.diyModules
import com.kmpdemo.home.di.homeModules
import com.kmpdemo.presentation.PlatformServices
import com.kmpdemo.presentation.di.presentationModules
import com.kmpdemo.profile.di.profileModules
import com.kmpdemo.schedule.di.scheduleModules
import com.kmpdemo.usecase.di.useCaseModules
import org.koin.core.module.Module
import org.koin.dsl.module
import org.kmpdemo.android.ui.PlatformServicesImpl
import org.kmpdemo.android.ui.viewmodel.MainViewModel

val appModule = module {

    //single(named("base_url")) { BuildConfig.BASE_URL }

    single {
        MainViewModel(preferences = get())
    }

    single<PlatformServices> { PlatformServicesImpl() }
}

private val featureModules = listOf(
    appModule,
    homeModules,
    diyModules,
    scheduleModules,
    profileModules,
    authenticationModules,
    presentationModules
)

val appModules: List<Module> = listOf(
    useCaseModules,
    featureModules
).flatten()