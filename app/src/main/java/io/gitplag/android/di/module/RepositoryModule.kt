package io.gitplag.android.di.module

import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.gitplag.android.client.GitplagClient
import io.gitplag.android.data.AnalysisRepository
import io.gitplag.android.data.RepositoryRepository

@Module
object RepositoryModule {

    @Provides
    @Reusable
    fun provideRepositoryRepository(client: GitplagClient) = RepositoryRepository(client)

    @Provides
    @Reusable
    fun provideAnalysisRepository(client: GitplagClient) = AnalysisRepository(client)

}