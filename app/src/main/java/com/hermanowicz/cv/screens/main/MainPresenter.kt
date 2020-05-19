package com.hermanowicz.cv.screens.main

import com.hermanowicz.cv.di.common.presenter.SubscribingPresenter
import com.hermanowicz.cv.di.common.transformer.RxTransformer
import com.hermanowicz.cv.usecase.GithubUseCase

class MainPresenter(transformer: RxTransformer, private val githubUseCase: GithubUseCase) :
    SubscribingPresenter<MainView>(transformer) {

    fun getData() {
        //TODO loader
        githubUseCase.getData().compose(transformer.single()).subscribe({
            view?.displayData(it)
        }, {}).remember()
    }
}
