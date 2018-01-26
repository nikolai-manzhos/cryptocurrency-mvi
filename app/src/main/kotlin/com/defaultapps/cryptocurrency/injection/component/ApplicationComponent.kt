package com.defaultapps.cryptocurrency.injection.component

import com.defaultapps.cryptocurrency.data.network.NetworkModule
import com.defaultapps.cryptocurrency.injection.module.ApplicationModule
import com.defaultapps.cryptocurrency.injection.module.RepositoryModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, NetworkModule::class, RepositoryModule::class])
interface ApplicationComponent {
    fun plusActivityComponent(): ActivityComponent
    fun plusScreenComponent(): ScreenComponent
}
