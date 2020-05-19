package com.hermanowicz.cv.network.service

import com.hermanowicz.cv.network.response.GithubResponse
import io.reactivex.Single
import retrofit2.http.GET

interface GithubService {

    @GET("b63d0d53ba6a3179b823d0ccaa21975a/raw/8e37d72f03e07c8b95b3c689bb8bbad453469550/data.json")
    fun getGithubData(): Single<GithubResponse>
}
