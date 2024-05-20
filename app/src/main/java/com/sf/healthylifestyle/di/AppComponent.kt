package com.sf.healthylifestyle.di

import android.content.Context
import com.sf.healthylifestyle.App
import com.sf.healthylifestyle.di.modules.AppModule
import com.sf.healthylifestyle.di.modules.DataModule
import com.sf.healthylifestyle.di.modules.DatabaseModule
import com.sf.healthylifestyle.di.modules.DomainModule
import com.sf.healthylifestyle.di.modules.MainModule
import com.sf.healthylifestyle.di.modules.RemoteModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import ed.maevski.imdb.di.modules.MappersModule
import javax.inject.Singleton

@Singleton
@Component(
    //Внедряем все модули, нужные для этого компонента
    modules = [
        AndroidInjectionModule::class,
        MainModule::class,
        AppModule::class,
        DatabaseModule::class,
        DataModule::class,
        DomainModule::class,
        MappersModule::class,
        RemoteModule::class,
    ]
)

interface AppComponent : AndroidInjector<App> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun withContext(context: Context): Builder
        fun build(): AppComponent
    }
}

