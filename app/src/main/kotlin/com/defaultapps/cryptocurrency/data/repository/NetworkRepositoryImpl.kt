package com.defaultapps.cryptocurrency.data.repository

import com.defaultapps.cryptocurrency.data.entity.Currency
import com.defaultapps.cryptocurrency.data.network.CoinApi
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkRepositoryImpl @Inject constructor(private val coinApi: CoinApi) : NetworkRepository {

    override fun getAllCryptocurrencies(): Single<List<Currency>> =
            coinApi.getCurrencies()
                    .delay(5, TimeUnit.SECONDS)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
}