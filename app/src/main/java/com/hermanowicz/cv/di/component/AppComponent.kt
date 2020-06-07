package com.hermanowicz.cv.di.component

import com.hermanowicz.cv.app.App
import com.hermanowicz.cv.di.module.AppModule
import com.hermanowicz.cv.di.module.BuilderModule
import com.hermanowicz.cv.modules.FormModule
import com.hermanowicz.cv.modules.MainModule
import com.hermanowicz.cv.modules.RepositoryModule
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
        MainModule::class,
        FormModule::class,
        RepositoryModule::class
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
