package com.defaultapps.cryptocurrency.data.repository

import com.defaultapps.cryptocurrency.domain.model.Currency
import io.reactivex.Observable

interface NetworkRepository {
    fun getAllCryptocurrencies(moneyType: String): Observable<List<Currency>>
}