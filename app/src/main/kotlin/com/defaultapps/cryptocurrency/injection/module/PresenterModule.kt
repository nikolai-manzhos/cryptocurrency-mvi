package com.defaultapps.cryptocurrency.injection.module

import com.defaultapps.cryptocurrency.view.detail.DetailContract.DetailPresenter
import com.defaultapps.cryptocurrency.view.detail.DetailPresenterImpl
import com.defaultapps.cryptocurrency.view.overview.OverviewContract.OverviewPresenter
import com.defaultapps.cryptocurrency.view.overview.OverviewPresenterImpl
import dagger.Binds
import dagger.Module

@Module
abstract class PresenterModule {

    @Binds
    abstract fun bindOverviewPresenter(overviewPresenterImpl: OverviewPresenterImpl): OverviewPresenter

    @Binds
    abstract fun bindDetailPresenter(detailPresenterImpl: DetailPresenterImpl): DetailPresenter
}
