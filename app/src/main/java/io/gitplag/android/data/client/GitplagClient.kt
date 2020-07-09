package io.gitplag.android.data.client

import io.gitplag.android.model.Analysis
import io.gitplag.android.model.Repository
import retrofit2.http.GET
import retrofit2.http.Path

interface GitplagClient {

    @GET("/api/repositories")
    suspend fun getRepositories(): List<Repository>

    @GET("/api/repositories/{id}")
    suspend fun getRepository(@Path("id") id: Long): Repository

    @GET("/api/repositories/{id}/analyzes")
    suspend fun getRepositoryAnalyzes(@Path("id") id: Long): List<Analysis>

    @GET("/api/analyzes/{id}")
    suspend fun getAnalysis(@Path("id") id: Long): Analysis

}