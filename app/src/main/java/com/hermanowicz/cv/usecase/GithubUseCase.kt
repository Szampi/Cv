package com.hermanowicz.cv.usecase

import com.hermanowicz.cv.model.CvData
import com.hermanowicz.cv.model.GithubData
import com.hermanowicz.cv.network.response.CvResponse
import com.hermanowicz.cv.network.response.GithubResponse
import com.hermanowicz.cv.network.service.GithubService
import io.reactivex.Single


class GithubUseCase(private val githubService: GithubService) {

    fun getData(): Single<GithubData> {
        return githubService.getGithubDataResponse().map { it.toGithubData() }
    }

    private fun GithubResponse.toGithubData(): GithubData {
        return GithubData(
            fullName = "${this.name} ${this.surname}",
            email = this.email,
            company = this.company,
            image = this.uri
        )
    }

    fun getCvData(): Single<List<CvData>> {
        return githubService.getCvData().map {
            it.map { element ->
                element.toCvData()
            }
        }
    }

    private fun CvResponse.toCvData(): CvData {
        return CvData(title = this.title, subtitles = this.tasks, expanded = false)
    }
}
