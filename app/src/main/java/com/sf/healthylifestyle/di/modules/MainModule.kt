package com.sf.healthylifestyle.di.modules


import com.sf.healthylifestyle.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface MainModule {

    @ContributesAndroidInjector
    fun bindMainActivity(): MainActivity

//    @ContributesAndroidInjector
//    fun bindHomeFragment(): HomeFragment


}