package com.defaultapps.cryptocurrency.view.overview

import com.defaultapps.cryptocurrency.domain.usecase.OverviewUseCase
import com.defaultapps.cryptocurrency.injection.scope.PerScreen
import com.defaultapps.cryptocurrency.view.base.BasePresenter
import com.defaultapps.cryptocurrency.view.overview.OverviewContract.OverviewController
import com.defaultapps.cryptocurrency.view.overview.OverviewContract.OverviewPresenter
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

@PerScreen
class OverviewPresenterImpl @Inject constructor(private val overviewUseCase: OverviewUseCase):
        BasePresenter<OverviewViewState, OverviewController>(), OverviewPresenter {

    override fun bindIntents() {
        compositeDisposable.add(
                view!!.loadData()
                        .flatMap { overviewUseCase.loadAllCryptocurrencies(isAttachedFirstTime) }
                        .subscribeBy( onNext = {view!!.render(it)})
        )
    }
}