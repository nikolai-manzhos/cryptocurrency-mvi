package com.defaultapps.cryptocurrency

import android.app.Application
import com.defaultapps.cryptocurrency.injection.component.ApplicationComponent
import com.defaultapps.cryptocurrency.injection.component.DaggerApplicationComponent
import com.defaultapps.cryptocurrency.injection.module.ApplicationModule

open class App : Application() {

    lateinit var applicationComponent: ApplicationComponent
        private set

    override fun onCreate() {
        super.onCreate()
        applicationComponent = initDaggerAppComponent().build()
    }

    protected fun initDaggerAppComponent(): DaggerApplicationComponent.Builder {
        return DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
    }
}