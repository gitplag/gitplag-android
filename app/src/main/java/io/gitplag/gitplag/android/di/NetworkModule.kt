package io.gitplag.gitplag.android.di

import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.gitplag.gitplag.android.BuildConfig
import io.gitplag.gitplag.android.client.GitplagClient
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
object NetworkModule {

    @Provides
    @Reusable
    fun provideOKHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .readTimeout(120, TimeUnit.SECONDS)
        .connectTimeout(120, TimeUnit.SECONDS)
        .build()

    @Provides
    @Reusable
    fun provideGSON(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Reusable
    fun provideRxJava2CallAdapterFactory(): RxJava2CallAdapterFactory = RxJava2CallAdapterFactory.create()

    @Provides
    @Reusable
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory,
        rxJava2CallAdapterFactory: RxJava2CallAdapterFactory
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.GITPLAG_SERVER_URL)
        .client(okHttpClient)
        .addConverterFactory(gsonConverterFactory)
        .addCallAdapterFactory(rxJava2CallAdapterFactory)
        .build()

    @Provides
    @Reusable
    fun provideGitplagClient(retrofit: Retrofit): GitplagClient = retrofit.create(GitplagClient::class.java)

}