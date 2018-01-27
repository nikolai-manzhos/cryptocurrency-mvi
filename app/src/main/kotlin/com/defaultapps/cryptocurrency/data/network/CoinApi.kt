package com.defaultapps.cryptocurrency.data.network

import com.defaultapps.cryptocurrency.data.entity.CurrencyResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface CoinApi {
    @GET("ticker/")
    fun getCurrencies(@Query("convert") moneyType: String): Single<List<CurrencyResponse>>
}
