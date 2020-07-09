package io.gitplag.android.di.module

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import io.gitplag.android.util.viewmodel.ViewModelProviderFactory

@Module
interface ViewModelFactoryModule {

    @Binds
    fun bindViewModelFactory(factory: ViewModelProviderFactory): ViewModelProvider.Factory
}