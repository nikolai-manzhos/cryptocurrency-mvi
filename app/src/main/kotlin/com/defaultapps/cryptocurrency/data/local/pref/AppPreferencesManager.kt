package com.defaultapps.cryptocurrency.data.local.pref

import android.content.SharedPreferences
import com.defaultapps.cryptocurrency.data.listener.OnPreferenceChange
import com.defaultapps.preferenceshelper.DefaultPreferencesManager
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppPreferencesManager @Inject constructor() : DefaultPreferencesManager() {

    private var preferenceListeners = HashMap<Int, SharedPreferences.OnSharedPreferenceChangeListener>()

    companion object {
        private const val MONEY_TYPE = "s_money_type"
        private const val DEFAULT_MONEY_TYPE = "USD"
    }

    fun setMoneyType(moneyType: String) = preferencesHelper.putString(MONEY_TYPE, moneyType)

    fun getMoneyType(): String = preferencesHelper.getString(MONEY_TYPE, DEFAULT_MONEY_TYPE)

    fun setOnPreferenceChangeListener(listener: OnPreferenceChange) {
        val preferenceListener = SharedPreferences.OnSharedPreferenceChangeListener { _, key -> listener.onPreferenceChange(key) }
        preferenceListeners[listener.hashCode()] = preferenceListener
        preferencesHelper.setOnSharedPreferencesChangeListener(preferenceListener)
    }

    fun removeOnPreferenceChangeListener(listener: OnPreferenceChange) {
        val removedListener = preferenceListeners.remove(listener.hashCode())
        preferencesHelper.removeOnSharedPreferencesChangeListener(removedListener)
    }
}