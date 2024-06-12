package com.sf.healthylifestyle.di.modules

import com.sf.healthylifestyle.data.api.AuthApi
import com.sf.healthylifestyle.data.repository.AuthRepository
import com.sf.healthylifestyle.domain.irepository.IAuthRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {
    @Provides
    @Singleton
    fun provideRepository(authApi: AuthApi) = AuthRepository(authApi = authApi)

/*    @Provides
    @Singleton
    fun provideRepository(
        authApi: AuthApi,
    ): IAuthRepository {
        return AuthRepository(
            authApi = authApi,
        )
    }*/
}