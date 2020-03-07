package com.example.contributors.model

import retrofit2.Response
import retrofit2.http.GET

interface IGitHubService {
    @GET("repositories/90792131/contributors")
    suspend fun getContributorList(): Response<List<Contributor>>
}
