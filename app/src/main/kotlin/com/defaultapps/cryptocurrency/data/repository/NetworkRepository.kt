package com.defaultapps.cryptocurrency.data.repository

import com.defaultapps.cryptocurrency.data.entity.Currency
import io.reactivex.Single

interface NetworkRepository {
    fun getAllCryptocurrencies(): Single<List<Currency>>
}