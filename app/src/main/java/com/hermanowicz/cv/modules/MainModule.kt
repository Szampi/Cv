package com.hermanowicz.cv.modules

import com.google.firebase.firestore.FirebaseFirestore
import com.hermanowicz.cv.di.common.transformer.RxTransformer
import com.hermanowicz.cv.repository.UserRepository
import com.hermanowicz.cv.screens.main.MainPresenter
import com.hermanowicz.cv.usecase.FirebaseUseCase
import dagger.Module
import dagger.Provides

@Module
class MainModule {

    @Provides
    fun provideMainPresenter(
        rxTransformer: RxTransformer,
        firebaseUseCase: FirebaseUseCase
    ): MainPresenter =
        MainPresenter(rxTransformer, firebaseUseCase)

    @Provides
    fun provideUseCase(firebaseFirestore: FirebaseFirestore, repository: UserRepository): FirebaseUseCase =
        FirebaseUseCase(firebaseFirestore, repository)
}
