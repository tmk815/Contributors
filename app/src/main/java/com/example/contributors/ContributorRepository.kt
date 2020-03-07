package com.example.contributors

import com.example.contributors.model.IGitHubService
import com.example.contributors.model.IGitHubService.Companion.GITHUB_API_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ContributorRepository {
    private val retrofit = Retrofit.Builder()
        .baseUrl(GITHUB_API_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private var githubService: IGitHubService = retrofit.create(IGitHubService::class.java)
}
