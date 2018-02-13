package com.defaultapps.cryptocurrency.data

import com.defaultapps.cryptocurrency.data.entity.CurrencyResponse
import com.defaultapps.cryptocurrency.data.repository.PreferenceRepository
import com.defaultapps.cryptocurrency.domain.model.Currency
import com.defaultapps.cryptocurrency.utils.Constants
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Mapper @Inject constructor(private val preferenceRepository: PreferenceRepository ) {

    fun currencyResponseToModel(currencyResponse: CurrencyResponse): Currency {
        val price = when(preferenceRepository.getMoneyType()) {
            MoneyType.USD -> currencyResponse.priceUsd + Constants.USD_SYMBOL
            MoneyType.CAD -> currencyResponse.priceCad + Constants.CAD_SYMBOL
            MoneyType.EUR -> currencyResponse.priceEur + Constants.EUR_SYMBOL
            MoneyType.RUB -> currencyResponse.priceRub + Constants.RUB_SYMBOL
        }
        return Currency(currencyResponse.id!!,
                currencyResponse.name!!,
                price,
                currencyResponse.percentChange24h?.toFloat() ?: Float.NaN,
                currencyResponse.percentChange24h?.toFloat() ?: Float.NaN,
                currencyResponse.percentChange7d?.toFloat() ?: Float.NaN)
    }
}
