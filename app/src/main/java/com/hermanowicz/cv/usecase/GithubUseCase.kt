package com.hermanowicz.cv.usecase

import com.hermanowicz.cv.model.GithubData
import com.hermanowicz.cv.network.response.GithubResponse
import com.hermanowicz.cv.network.service.GithubService
import io.reactivex.Single


class GithubUseCase(private val githubService: GithubService) {

    fun getData(): Single<GithubData> {
        return githubService.getGithubData().map { it.toGithubData() }
    }

    private fun GithubResponse.toGithubData(): GithubData {
        return GithubData(
            fullName = "${this.name} ${this.surname}",
            email = this.email,
            company = this.company,
            image = this.uri
        )
    }
}
