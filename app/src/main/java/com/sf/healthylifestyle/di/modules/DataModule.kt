package com.sf.healthylifestyle.di.modules

import com.sf.healthylifestyle.data.api.AuthApi
import com.sf.healthylifestyle.data.api.ProductApi
import com.sf.healthylifestyle.data.dbo.dao.BasketDao
import com.sf.healthylifestyle.data.mappers.dish.DishMapper
import com.sf.healthylifestyle.data.repository.AuthRepository
import com.sf.healthylifestyle.data.repository.BasketRepository
import com.sf.healthylifestyle.data.repository.ProductRepository
import com.sf.healthylifestyle.domain.irepository.IAuthRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {
    @Provides
    @Singleton
    fun provideAuthRepository(authApi: AuthApi) = AuthRepository(authApi = authApi)

    @Provides
    @Singleton
    fun provideProductRepository(productApi: ProductApi,dishMapper: DishMapper) =
        ProductRepository(productApi = productApi, dishMapper = dishMapper)

    @Provides
    @Singleton
    fun provideBasketRepository(
        basketDao: BasketDao,
        dishMapper: DishMapper
    ) = BasketRepository(
        basketDao = basketDao,
        dishMapper = dishMapper
    )

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