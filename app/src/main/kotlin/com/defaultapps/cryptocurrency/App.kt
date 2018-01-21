package com.defaultapps.cryptocurrency

import android.annotation.SuppressLint
import android.app.Application
import com.defaultapps.cryptocurrency.injection.component.ApplicationComponent
import com.defaultapps.cryptocurrency.injection.component.DaggerApplicationComponent
import com.defaultapps.cryptocurrency.injection.module.ApplicationModule
import com.defaultapps.cryptocurrency.utils.Constants
import com.defaultapps.preferenceshelper.PreferencesHelper

@SuppressLint("Registered")
open class App : Application() {

    lateinit var applicationComponent: ApplicationComponent
        private set

    override fun onCreate() {
        super.onCreate()
        initPreferencesHelper()
        applicationComponent = provideDaggerAppComponent().build()
    }

    protected fun provideDaggerAppComponent(): DaggerApplicationComponent.Builder {
        return DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
    }

    private fun initPreferencesHelper() {
        PreferencesHelper.builder(this)
                .setName(Constants.PREF_NAME)
                .build()
    }
}