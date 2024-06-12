package com.sf.healthylifestyle.di.modules

import com.sf.healthylifestyle.data.repository.AuthRepository
import com.sf.healthylifestyle.domain.usecases.GetTokenByEmail
import com.sf.healthylifestyle.domain.usecases.GetTokenByPhone
import com.sf.healthylifestyle.domain.usecases.LoginUseCase
import com.sf.healthylifestyle.domain.usecases.RegisterUser
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

    @Singleton
    @Provides
    fun provideGetTokenByEmail(repository: AuthRepository): GetTokenByEmail {
        return GetTokenByEmail(repository = repository)
    }

    @Singleton
    @Provides
    fun provideRegisterUser(repository: AuthRepository): RegisterUser {
        return RegisterUser(repository = repository)
    }

    @Singleton
    @Provides
    fun provideLoginUseCase(repository: AuthRepository): LoginUseCase {
        return LoginUseCase(repository = repository)
    }

}