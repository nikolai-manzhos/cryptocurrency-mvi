package com.defaultapps.cryptocurrency.data.network

import com.defaultapps.cryptocurrency.data.entity.CurrencyResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CoinApi {
    @GET("ticker/")
    fun getCurrencies(@Query("convert") moneyType: String)
            : Observable<List<CurrencyResponse>>

    @GET("ticker/{id}/")
    fun getCurrencyDetail(@Path("id") id: String, @Query("convert") moneyType: String)
            : Observable<List<CurrencyResponse>>
}
