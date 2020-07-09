package io.gitplag.android.di.module

import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.gitplag.android.data.client.GitplagClient
import io.gitplag.android.data.repository.AnalysisRepository
import io.gitplag.android.data.repository.RepositoryRepository

@Module
object RepositoryModule {

    @Provides
    @Reusable
    fun provideRepositoryRepository(client: GitplagClient) =
        RepositoryRepository(client)

    @Provides
    @Reusable
    fun provideAnalysisRepository(client: GitplagClient) =
        AnalysisRepository(client)

}