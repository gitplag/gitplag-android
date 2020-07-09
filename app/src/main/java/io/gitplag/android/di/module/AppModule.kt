package io.gitplag.android.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.gitplag.android.ui.analysis.AnalysisActivity
import io.gitplag.android.ui.main.MainActivity
import io.gitplag.android.ui.repositories.RepositoryListActivity
import io.gitplag.android.ui.repository.RepositoryActivity

@Module
abstract class AppModule {
    @ContributesAndroidInjector(modules = [NetworkModule::class, RepositoryModule::class])
    abstract fun mainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [NetworkModule::class, RepositoryModule::class])
    abstract fun repositoryListActivity(): RepositoryListActivity

    @ContributesAndroidInjector(modules = [NetworkModule::class, RepositoryModule::class])
    abstract fun repositoryActivity(): RepositoryActivity

    @ContributesAndroidInjector(modules = [NetworkModule::class, RepositoryModule::class])
    abstract fun analysisActivity(): AnalysisActivity
}