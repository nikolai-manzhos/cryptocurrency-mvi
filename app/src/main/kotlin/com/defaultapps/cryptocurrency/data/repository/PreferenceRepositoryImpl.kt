package com.defaultapps.cryptocurrency.data.repository

import com.defaultapps.cryptocurrency.data.MoneyType
import com.defaultapps.cryptocurrency.data.listener.OnPreferenceChange
import com.defaultapps.cryptocurrency.data.local.pref.AppPreferencesManager
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class PreferenceRepositoryImpl
@Inject constructor(private val appPreferencesManager: AppPreferencesManager) : PreferenceRepository {

    override fun getMoneyType(): MoneyType = MoneyType.valueOf(appPreferencesManager.getMoneyType())

    override fun setMoneyType(moneyType: MoneyType) = appPreferencesManager.setMoneyType(moneyType.name)

    override fun setChangeListener(listener: OnPreferenceChange) {
        appPreferencesManager.setOnPreferenceChangeListener(listener)
    }

    override fun removeChangeListener(listener: OnPreferenceChange) {
        appPreferencesManager.removeOnPreferenceChangeListener(listener)
    }

}