package io.gitplag.android.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.gitplag.android.activity.AnalysisActivity
import io.gitplag.android.activity.MainActivity
import io.gitplag.android.activity.RepositoryActivity
import io.gitplag.android.activity.RepositoryListActivity

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
}