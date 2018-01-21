package com.defaultapps.cryptocurrency.data.repository

import com.defaultapps.cryptocurrency.data.MoneyType
import com.defaultapps.cryptocurrency.data.listener.OnPreferenceChange


interface PreferenceRepository {
    fun getMoneyType(): MoneyType
    fun setMoneyType(moneyType: MoneyType)

    fun setChangeListener(listener: OnPreferenceChange)
    fun removeChangeListener(listener: OnPreferenceChange)
}