package com.hermanowicz.cv.modules

import android.content.SharedPreferences
import com.hermanowicz.cv.repository.SharedPrefsUser
import com.hermanowicz.cv.repository.UserRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun userRepository(sharedPreferences: SharedPreferences): UserRepository =
        SharedPrefsUser(sharedPreferences)
}
