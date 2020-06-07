package com.hermanowicz.cv.modules

import com.google.firebase.firestore.FirebaseFirestore
import com.hermanowicz.cv.di.common.transformer.RxTransformer
import com.hermanowicz.cv.network.service.GithubService
import com.hermanowicz.cv.screens.main.MainPresenter
import com.hermanowicz.cv.usecase.GithubUseCase
import dagger.Module
import dagger.Provides

@Module
class MainModule {

    @Provides
    fun provideMainPresenter(
        transformer: RxTransformer,
        useCase: GithubUseCase,
        firebaseDB: FirebaseFirestore
    ): MainPresenter =
        MainPresenter(transformer, useCase, firebaseDB)

    @Provides
    fun provideUseCase(githubService: GithubService): GithubUseCase = GithubUseCase(githubService)
}
