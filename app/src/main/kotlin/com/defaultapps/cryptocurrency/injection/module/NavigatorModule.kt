package com.defaultapps.cryptocurrency.injection.module

import com.defaultapps.cryptocurrency.view.overview.OverviewContract
import com.defaultapps.cryptocurrency.view.overview.OverviewNavigatorImpl
import dagger.Binds
import dagger.Module

@Module
abstract class NavigatorModule {
    @Binds
    abstract fun bindOverviewNavigator(overviewNavigatorImpl: OverviewNavigatorImpl): OverviewContract.OverviewNavigator
}
