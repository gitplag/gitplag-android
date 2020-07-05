package io.gitplag.gitplag.android.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import io.gitplag.gitplag.android.activity.AnalysisActivity
import io.gitplag.gitplag.android.activity.MainActivity
import io.gitplag.gitplag.android.activity.RepositoryActivity
import io.gitplag.gitplag.android.activity.RepositoryListActivity
import javax.inject.Singleton

@Module
abstract class AppModule {
    @ContributesAndroidInjector(modules = [NetworkModule::class])
    abstract fun mainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [NetworkModule::class])
    abstract fun repositoryListActivity(): RepositoryListActivity

    @ContributesAndroidInjector(modules = [NetworkModule::class])
    abstract fun repositoryActivity(): RepositoryActivity

    @ContributesAndroidInjector(modules = [NetworkModule::class])
    abstract fun analysisActivity(): AnalysisActivity

    companion object {
        @Provides
        @Singleton
        fun context(app: GitplagAndroidApp): Context {
            return app.applicationContext
        }
    }
}