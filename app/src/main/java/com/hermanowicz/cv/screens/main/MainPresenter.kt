package com.hermanowicz.cv.screens.main

import android.util.Log
import com.hermanowicz.cv.di.common.presenter.SubscribingPresenter
import com.hermanowicz.cv.di.common.transformer.RxTransformer
import com.hermanowicz.cv.usecase.GithubUseCase

class MainPresenter(transformer: RxTransformer, private val githubUseCase: GithubUseCase) :
    SubscribingPresenter<MainView>(transformer) {

    fun getData() {
        githubUseCase.getData().compose(transformer.single()).subscribe({
            view?.initView(it)
            getCvData()
        }, {
            //TODO handle error
        }).remember()
    }

    private fun getCvData() {
        githubUseCase.getCvData().compose(transformer.single()).subscribe({
            Log.d("DATA :: ", "$it")
            view?.displayCv(it)
        }, {
            Log.d("ERROR :: ", "$it")
            //TODO handle error
        }).remember()
    }

}
