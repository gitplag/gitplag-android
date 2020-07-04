package io.gitplag.gitplag.android.client

import io.gitplag.gitplag.android.model.Repository
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface GitplagClient {

    @GET("/api/repositories")
    fun getRepositories(): Observable<List<Repository>>

    @GET("/api/repositories/{id}")
    fun getRepository(@Path("id") id: Long): Observable<Repository>

}