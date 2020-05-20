package com.hermanowicz.cv.di.component

import com.hermanowicz.cv.app.App
import com.hermanowicz.cv.di.module.AppModule
import com.hermanowicz.cv.di.module.BuilderModule
import com.hermanowicz.cv.modules.MainModule
import com.hermanowicz.cv.modules.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        BuilderModule::class,
        NetworkModule::class,
        MainModule::class
    ]
)

interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: App): Builder

        fun build(): AppComponent
    }

    fun inject(app: App)

}