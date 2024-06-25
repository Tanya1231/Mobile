package com.sf.healthylifestyle.di.modules


import com.sf.healthylifestyle.view.MainActivity
import com.sf.healthylifestyle.view.auth.AuthFragment
import com.sf.healthylifestyle.view.basket.BasketFragment
import com.sf.healthylifestyle.view.book.BookFragment
import com.sf.healthylifestyle.view.catalogue.CatalogueFragment
import com.sf.healthylifestyle.view.confirm.ConfirmFragment
import com.sf.healthylifestyle.view.home.HomeFragment
import com.sf.healthylifestyle.view.mydish.MyDishFragment
import com.sf.healthylifestyle.view.onboarding.OnboardingFourFragment
import com.sf.healthylifestyle.view.onboarding.OnboardingOneFragment
import com.sf.healthylifestyle.view.onboarding.OnboardingThreeFragment
import com.sf.healthylifestyle.view.onboarding.OnboardingTwoFragment
import com.sf.healthylifestyle.view.profile.ProfileFragment
import com.sf.healthylifestyle.view.register.RegisterFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface MainModule {

    @ContributesAndroidInjector
    fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector
    fun bindHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    fun bindAuthFragment(): AuthFragment

    @ContributesAndroidInjector
    fun bindRegisterFragment(): RegisterFragment

    @ContributesAndroidInjector
    fun bindConfirmFragment(): ConfirmFragment

    @ContributesAndroidInjector
    fun bindBookFragment(): BookFragment

    @ContributesAndroidInjector
    fun bindCatalogueFragment(): CatalogueFragment

    @ContributesAndroidInjector
    fun bindMyDishFragment(): MyDishFragment

    @ContributesAndroidInjector
    fun bindMyProfileFragment(): ProfileFragment

    @ContributesAndroidInjector
    fun bindBasketFragment(): BasketFragment

    @ContributesAndroidInjector
    fun bindOnboardingOneFragment(): OnboardingOneFragment
    @ContributesAndroidInjector
    fun bindOnboardingTwoFragment(): OnboardingTwoFragment
    @ContributesAndroidInjector
    fun bindOnboardingThreeFragment(): OnboardingThreeFragment

    @ContributesAndroidInjector
    fun bindOnboardingFourFragment(): OnboardingFourFragment
}