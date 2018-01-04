package com.defaultapps.cryptocurrency.data.repository

import com.defaultapps.cryptocurrency.data.network.CoinApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkRepositoryImpl @Inject constructor(private val coinApi: CoinApi): NetworkRepository {

    override fun getAllCryptocurrencies() =
            coinApi.getCurrencies()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
}