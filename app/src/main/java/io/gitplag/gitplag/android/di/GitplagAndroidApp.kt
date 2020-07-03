package io.gitplag.gitplag.android.di

import dagger.android.DaggerApplication

class GitplagAndroidApp : DaggerApplication() {
    private val applicationInjector = DaggerAppComponent.builder()
        .application(this)?.build()

    override fun applicationInjector() = applicationInjector
}