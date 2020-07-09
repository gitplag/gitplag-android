package io.gitplag.android.di.module

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import io.gitplag.android.ui.analysis.AnalysisViewModel
import io.gitplag.android.ui.repositories.RepositoryListViewModel
import io.gitplag.android.ui.repository.RepositoryViewModel

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(RepositoryListViewModel::class)
    abstract fun bindRepositoryListViewModel(viewModel: RepositoryListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AnalysisViewModel::class)
    abstract fun bindAnalysisViewModel(viewModel: AnalysisViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RepositoryViewModel::class)
    abstract fun bindRepositoryViewModel(viewModel: RepositoryViewModel): ViewModel
}