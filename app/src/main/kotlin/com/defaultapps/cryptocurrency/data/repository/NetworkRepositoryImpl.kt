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
    
    companion object {
        const val SINGLE_ITEM = 0
    }

    override fun getAllCryptocurrencies(moneyType: String): Observable<List<Currency>> =
            coinApi.getCurrencies(moneyType)
                    .map { it.map { mapper.currencyResponseToModel(it) } }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())

    override fun getCryptocurrencyDetail(id: String, moneyType: String): Observable<Currency> =
            coinApi.getCurrencyDetail(id, moneyType)
                    .map { mapper.currencyResponseToModel(it[SINGLE_ITEM]) }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
}
