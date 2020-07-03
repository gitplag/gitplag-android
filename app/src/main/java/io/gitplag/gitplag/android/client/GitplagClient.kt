package io.gitplag.gitplag.android.client

import io.gitplag.gitplag.android.BuildConfig.GITPLAG_SERVER_URL
import io.gitplag.gitplag.android.model.Repository
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface GitplagClient {

    @GET("/api/repositories")
    fun getRepositories(): Observable<List<Repository>>

    @GET("/api/repositories/{id}")
    fun getRepository(@Path("id") id: Long): Observable<Repository>

    companion object Factory {
        fun create(): GitplagClient = Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(GITPLAG_SERVER_URL)
            .build()
            .create(GitplagClient::class.java)
    }
}