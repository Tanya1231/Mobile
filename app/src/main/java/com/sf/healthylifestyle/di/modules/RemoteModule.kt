package com.sf.healthylifestyle.di.modules

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class RemoteModule {
/*    @Provides
    @Singleton
    fun provideTestOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        //Настраиваем таймауты для медленного интернета
        .callTimeout(HALF_MINUTE_FOR_SLOW_INTERNET, TimeUnit.SECONDS)
        .readTimeout(HALF_MINUTE_FOR_SLOW_INTERNET, TimeUnit.SECONDS)
        .addInterceptor(
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
        .addInterceptor { chain ->
            chain.proceed(
                chain.request()
                    .newBuilder().apply {
                        addHeader(
                            "X-RapidAPI-Key",
                            "efe0254885msh3087de471ebfdfdp1d3674jsn5e68aebd177d"
                        )
                        addHeader("X-RapidAPI-Host", "ott-details.p.rapidapi.com")
                    }
                    .build()
            )
        }
        .build()*/

/*    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        //Указываем базовый URL из констант
        .baseUrl(BASE_URL)
        //Добавляем конвертер
        .addConverterFactory(GsonConverterFactory.create())
        //Добавляем кастомный клиент
        .client(okHttpClient)
        .build()*/

/*    @Provides
    @Singleton
    fun provideMaiboApi(retrofit: Retrofit): OttApi = retrofit.create(OttApi::class.java)*/

//    companion object {
//        private const val HALF_MINUTE_FOR_SLOW_INTERNET = 30L
////        const val BASE_URL = "https://ott-details.p.rapidapi.com/"
//    }
}