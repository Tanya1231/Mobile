package com.sf.healthylifestyle.di.modules

import com.sf.healthylifestyle.domain.usecases.ConfirmUseCase
import com.sf.healthylifestyle.domain.usecases.LoginUseCase
import com.sf.healthylifestyle.domain.usecases.RegisterUseCase
import com.sf.healthylifestyle.view.auth.AuthViewModel
import com.sf.healthylifestyle.view.confirm.ConfirmViewModel
import com.sf.healthylifestyle.view.register.RegisterViewModel
import dagger.Module
import dagger.Provides

@Module
class AppModule() {
    @Provides
    fun provideAuthViewModelFactory(
        loginUseCase: LoginUseCase,
        confirmUseCase: ConfirmUseCase,
        ) = AuthViewModel.Factory(
        loginUseCase = loginUseCase,
        confirmUseCase = confirmUseCase,
    )


    @Provides
    fun provideRegisterViewModelFactory(
        regUseCase: RegisterUseCase,
        confirmUseCase: ConfirmUseCase,

        ) = RegisterViewModel.Factory(
        regUseCase = regUseCase,
        confirmUseCase = confirmUseCase,
    )

    @Provides
    fun provideConfirmViewModelFactory(
        confirmUseCase: ConfirmUseCase,
        ) = ConfirmViewModel.Factory(
        confirmUseCase = confirmUseCase,
    )

}
