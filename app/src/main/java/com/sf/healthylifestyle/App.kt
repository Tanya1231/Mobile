package com.sf.healthylifestyle

import com.sf.healthylifestyle.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class App : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<App> =
        DaggerAppComponent.builder().withContext(applicationContext).build()
}