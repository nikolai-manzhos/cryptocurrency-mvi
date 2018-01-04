package com.defaultapps.cryptocurrency.injection.module

import com.defaultapps.cryptocurrency.view.overview.OverviewContract
import com.defaultapps.cryptocurrency.view.overview.OverviewPresenterImpl
import dagger.Binds
import dagger.Module

@Module
abstract class PresenterModule {

    @Binds
    abstract fun bindOverviewPresenter(overviewPresenterImpl: OverviewPresenterImpl): OverviewContract.OverviewPresenter
}