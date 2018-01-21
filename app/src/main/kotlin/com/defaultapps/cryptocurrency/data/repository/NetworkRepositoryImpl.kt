package com.defaultapps.cryptocurrency.data.repository

import com.defaultapps.cryptocurrency.data.Mapper
import com.defaultapps.cryptocurrency.data.network.CoinApi
import com.defaultapps.cryptocurrency.domain.model.Currency
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkRepositoryImpl @Inject constructor(private val coinApi: CoinApi,
                                                private val mapper: Mapper) : NetworkRepository {

    override fun getAllCryptocurrencies(moneyType: String): Observable<List<Currency>> =
            coinApi.getCurrencies(moneyType)
                    .map { it.map { mapper.currencyResponseToModel(it) } }
                    .toObservable()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
}