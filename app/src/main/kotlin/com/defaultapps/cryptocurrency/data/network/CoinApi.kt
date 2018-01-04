package com.defaultapps.cryptocurrency.data.network

import com.defaultapps.cryptocurrency.data.entity.Currency
import io.reactivex.Single
import retrofit2.http.GET

interface CoinApi {
    @GET("ticker/")
    fun getCurrencies(): Single<List<Currency>>
}