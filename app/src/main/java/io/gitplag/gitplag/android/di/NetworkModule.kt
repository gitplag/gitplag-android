package io.gitplag.gitplag.android.di

import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.gitplag.gitplag.android.client.GitplagClient

@Module
object NetworkModule {

    @Provides
    @Reusable
    fun providePostApi(): GitplagClient = GitplagClient.create()

}