package com.hermanowicz.cv.modules

import com.hermanowicz.cv.di.common.transformer.RxTransformer
import com.hermanowicz.cv.screens.form.FormPresenter
import dagger.Module
import dagger.Provides

@Module
class FormModule {

    @Provides
    fun formPresenter(transformer: RxTransformer): FormPresenter = FormPresenter(transformer)
}
