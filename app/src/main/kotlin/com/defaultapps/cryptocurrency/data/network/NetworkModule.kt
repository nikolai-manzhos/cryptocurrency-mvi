package com.defaultapps.cryptocurrency.data.network

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideCoinApi(): CoinApi {
        val retrofit = provideRetrofit()
        return retrofit.create(CoinApi::class.java)
    }

    private fun provideRetrofit(): Retrofit {
        val baseUrl = "https://api.coinmarketcap.com/v1/"
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(baseUrl)
                .build()
    }
}