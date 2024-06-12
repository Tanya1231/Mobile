package com.sf.healthylifestyle.di.modules

import com.sf.healthylifestyle.domain.usecases.GetTokenByEmail
import com.sf.healthylifestyle.domain.usecases.GetTokenByPhone
import com.sf.healthylifestyle.domain.usecases.LoginUseCase
import com.sf.healthylifestyle.domain.usecases.RegisterUser
import com.sf.healthylifestyle.view.auth.AuthViewModel
import com.sf.healthylifestyle.view.confirm.ConfirmViewModel
import com.sf.healthylifestyle.view.profile.ProfileViewModel
import com.sf.healthylifestyle.view.register.RegisterViewModel
import dagger.Module
import dagger.Provides

@Module
class AppModule() {
    @Provides
    fun provideAuthViewModelFactory(
        loginUseCase: LoginUseCase,
        ) = AuthViewModel.Factory(
        loginUseCase = loginUseCase,
    )


    @Provides
    fun provideRegisterViewModelFactory(
        registerUser: RegisterUser,

        ) = RegisterViewModel.Factory(
        registerUser = registerUser,
    )

    @Provides
    fun provideConfirmViewModelFactory(

        ) = ConfirmViewModel.Factory(

    )

}
