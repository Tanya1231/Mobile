package com.sf.healthylifestyle.di.modules

import android.content.Context
import androidx.room.Room
import com.sf.healthylifestyle.data.dbo.db.HealthyLifestyleDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun provideDb(context: Context) = Room.databaseBuilder(
        context, HealthyLifestyleDatabase::class.java, "healthyLifestyle_db"
    ).fallbackToDestructiveMigration().build()

    @Singleton
    @Provides
    fun provideMovieDao(context: Context) = provideDb(context).basketDao()
}

