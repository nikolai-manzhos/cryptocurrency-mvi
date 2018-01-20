package com.defaultapps.cryptocurrency

import android.annotation.SuppressLint
import android.app.Application
import com.defaultapps.cryptocurrency.injection.component.ApplicationComponent
import com.defaultapps.cryptocurrency.injection.component.DaggerApplicationComponent
import com.defaultapps.cryptocurrency.injection.module.ApplicationModule

@SuppressLint("Registered")
open class App : Application() {

    lateinit var applicationComponent: ApplicationComponent
        private set

    override fun onCreate() {
        super.onCreate()
        applicationComponent = initDaggerAppComponent().build()
    }

    protected fun initDaggerAppComponent(): DaggerApplicationComponent.Builder {
        return DaggerApplicationComponent.builder()
    }
}