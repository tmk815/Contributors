package com.example.contributors.model

import retrofit2.Response
import retrofit2.http.GET

interface IGitHubService {
    companion object {
        const val GITHUB_API_URL = "https://api.github.com/"
    }

    @GET("repositories/90792131/contributors")
    suspend fun getContributorList(): Response<List<Contributor>>
}
