package com.defaultapps.cryptocurrency.view.detail

import com.defaultapps.cryptocurrency.domain.usecase.DetailUseCase
import com.defaultapps.cryptocurrency.injection.scope.PerScreen
import com.defaultapps.cryptocurrency.view.base.BasePresenter
import com.defaultapps.cryptocurrency.view.detail.DetailContract.DetailPresenter
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

@PerScreen
class DetailPresenterImpl @Inject constructor(private val detailUseCase: DetailUseCase) :
        BasePresenter<DetailViewState, DetailContract.DetailController>(), DetailPresenter {

    override fun bindIntents(view: DetailContract.DetailController) {
        compositeDisposable += view.initialLoad()
                .switchMap { detailUseCase.loadCurrencyDetail(isAttachedFirstTime, it) }
                .subscribeBy( onNext = { view.render(it) })
    }
}
