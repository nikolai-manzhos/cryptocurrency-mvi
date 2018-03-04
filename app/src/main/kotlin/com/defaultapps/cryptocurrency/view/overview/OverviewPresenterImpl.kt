package com.defaultapps.cryptocurrency.view.overview

import com.defaultapps.cryptocurrency.domain.usecase.OverviewUseCase
import com.defaultapps.cryptocurrency.injection.scope.PerScreen
import com.defaultapps.cryptocurrency.view.base.BasePresenter
import com.defaultapps.cryptocurrency.view.overview.OverviewContract.OverviewController
import com.defaultapps.cryptocurrency.view.overview.OverviewContract.OverviewPresenter
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

@PerScreen
class OverviewPresenterImpl @Inject constructor(private val overviewUseCase: OverviewUseCase) :
        BasePresenter<OverviewViewState, OverviewController>(), OverviewPresenter {

    override fun bindIntents(view: OverviewController) {
        compositeDisposable += Observable.merge(
                initialLoad(view.initialLoad(), isAttachedFirstTime),
                initialLoad(view.retryAction(), true))
                .subscribeBy( onNext = {view.render(it)})
    }

    private fun initialLoad(observable: Observable<Unit>, force: Boolean): Observable<OverviewViewState> {
        return observable
                .switchMap { overviewUseCase.loadAllCryptocurrencies(force) }
    }
}
