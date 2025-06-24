package com.kmpdemo.schedule.di

import com.kmpdemo.schedule.ui.viewmodel.ScheduleViewModel
import org.koin.dsl.module

val scheduleModules = module {

    single{
        ScheduleViewModel()
    }
}