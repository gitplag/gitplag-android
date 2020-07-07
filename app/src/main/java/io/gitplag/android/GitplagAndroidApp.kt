package io.gitplag.android

import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import io.gitplag.android.di.component.DaggerAppComponent
import javax.inject.Inject

class GitplagAndroidApp : Application(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent.builder()
            .create(this)
            .build()
            .inject(this)

    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

}