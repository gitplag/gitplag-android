package io.gitplag.android.di.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import io.gitplag.android.GitplagAndroidApp
import io.gitplag.android.di.module.AppModule
import io.gitplag.android.di.module.NetworkModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, NetworkModule::class, AppModule::class])
interface AppComponent : AndroidInjector<GitplagAndroidApp> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun create(application: Application): Builder
        fun build(): AppComponent
    }

    override fun inject(app: GitplagAndroidApp)
}