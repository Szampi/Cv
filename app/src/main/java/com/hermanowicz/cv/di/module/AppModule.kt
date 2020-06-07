package com.hermanowicz.cv.di.module

import android.content.Context
import android.content.SharedPreferences
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.hermanowicz.cv.app.App
import com.hermanowicz.cv.di.common.transformer.RxTransformer
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    fun applicationContext(app: App): Context {
        return app
    }

    @Provides
    fun rxTransformer() = RxTransformer()

    @Provides
    @Singleton
    fun gson(): Gson = GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .create()

    @Provides
    @Singleton
    fun firebaseDatabase(): FirebaseFirestore = FirebaseFirestore.getInstance()

    @Provides
    @Singleton
    fun sharedPreferences(applicationContext: Context): SharedPreferences {
        return applicationContext.getSharedPreferences(
            App::class.java.canonicalName,
            Context.MODE_PRIVATE
        )
    }
}
