package com.hermanowicz.cv.di.module

import com.hermanowicz.cv.modules.MainModule
import com.hermanowicz.cv.screens.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface BuilderModule {

    @ContributesAndroidInjector(modules = [MainModule::class])
    fun bindMainActivity(): MainActivity
}
