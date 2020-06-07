package com.hermanowicz.cv.modules

import com.hermanowicz.cv.di.common.transformer.RxTransformer
import com.hermanowicz.cv.screens.form.FormPresenter
import com.hermanowicz.cv.usecase.FirebaseUseCase
import dagger.Module
import dagger.Provides

@Module
class FormModule {

    @Provides
    fun formPresenter(transformer: RxTransformer, firebaseUseCase: FirebaseUseCase): FormPresenter =
        FormPresenter(transformer, firebaseUseCase)
}
