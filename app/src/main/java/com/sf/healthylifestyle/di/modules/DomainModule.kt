package com.sf.healthylifestyle.di.modules

import com.sf.healthylifestyle.data.repository.AuthRepository
import com.sf.healthylifestyle.data.repository.ProductRepository
import com.sf.healthylifestyle.domain.usecases.ConfirmUseCase
import com.sf.healthylifestyle.domain.usecases.GetLeftHalfUseCase
import com.sf.healthylifestyle.domain.usecases.GetRightHalfUseCase
import com.sf.healthylifestyle.domain.usecases.GetTokenByEmail
import com.sf.healthylifestyle.domain.usecases.GetTokenByPhone
import com.sf.healthylifestyle.domain.usecases.LoginUseCase
import com.sf.healthylifestyle.domain.usecases.RegisterUseCase
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
    fun provideRegisterUser(repository: AuthRepository): RegisterUseCase {
        return RegisterUseCase(repository = repository)
    }

    @Singleton
    @Provides
    fun provideLoginUseCase(repository: AuthRepository): LoginUseCase {
        return LoginUseCase(repository = repository)
    }

    @Singleton
    @Provides
    fun provideConfirmUseCase(repository: AuthRepository): ConfirmUseCase {
        return ConfirmUseCase(repository = repository)
    }

    @Singleton
    @Provides
    fun provideGetLeftHalfUseCase(repository: ProductRepository): GetLeftHalfUseCase {
        return GetLeftHalfUseCase(repository = repository)
    }

    @Singleton
    @Provides
    fun provideGetRightHalfUseCase(repository: ProductRepository): GetRightHalfUseCase {
        return GetRightHalfUseCase(repository = repository)
    }

}