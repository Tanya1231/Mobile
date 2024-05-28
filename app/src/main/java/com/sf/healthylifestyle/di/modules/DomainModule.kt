package com.sf.healthylifestyle.di.modules

import com.sf.healthylifestyle.data.repository.AuthRepository
import com.sf.healthylifestyle.domain.usecases.GetTokenByPhone
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DomainModule {

    @Singleton
    @Provides
    fun provideGetTokenByPhone(repository: AuthRepository): GetTokenByPhone {
        return GetTokenByPhone(repository = repository)
    }

}