package com.defaultapps.cryptocurrency.view.overview

import com.defaultapps.cryptocurrency.view.base.MviPresenter
import com.defaultapps.cryptocurrency.view.base.MviView
import io.reactivex.Observable

interface OverviewContract {
    interface OverviewController: MviView<OverviewViewState> {
        fun loadData(): Observable<Boolean>
    }
    interface OverviewPresenter: MviPresenter<OverviewViewState, OverviewController>
}