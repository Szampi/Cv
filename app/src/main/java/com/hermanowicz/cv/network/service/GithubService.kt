package com.hermanowicz.cv.network.service

import com.hermanowicz.cv.network.response.CvResponse
import com.hermanowicz.cv.network.response.GithubResponse
import io.reactivex.Single
import retrofit2.http.GET

interface GithubService {

    @GET("b63d0d53ba6a3179b823d0ccaa21975a/raw/8e37d72f03e07c8b95b3c689bb8bbad453469550/data.json")
    fun getGithubDataResponse(): Single<GithubResponse>

    @GET("94c2960075a2e3afa0e45d307a8c0f16/raw/242a75bdc61e1f0e9425632dfa4b43fb074e1b61/cv.json")
    fun getCvData(): Single<List<CvResponse>>

}
