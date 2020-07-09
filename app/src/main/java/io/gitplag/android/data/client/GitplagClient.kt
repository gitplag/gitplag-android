package io.gitplag.android.data.client

import io.gitplag.android.model.Analysis
import io.gitplag.android.model.Repository
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface GitplagClient {

    @GET("/api/repositories")
    fun getRepositories(): Observable<List<Repository>>

    @GET("/api/repositories/{id}")
    fun getRepository(@Path("id") id: Long): Observable<Repository>

    @GET("/api/repositories/{id}/analyzes")
    fun getRepositoryAnalyzes(@Path("id") id: Long): Observable<List<Analysis>>

    @GET("/api/analyzes/{id}")
    fun getAnalysis(@Path("id") id: Long): Observable<Analysis>

}