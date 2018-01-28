package com.defaultapps.cryptocurrency.view.overview

import com.defaultapps.cryptocurrency.view.base.MviPresenter
import com.defaultapps.cryptocurrency.view.base.MviView
import com.defaultapps.cryptocurrency.view.base.Navigator
import io.reactivex.Observable

interface OverviewContract {
    interface OverviewController: MviView<OverviewViewState> {
        fun retryAction(): Observable<Unit>
        fun loadData(): Observable<Unit>
    }
    interface OverviewPresenter: MviPresenter<OverviewViewState, OverviewController>
    interface OverviewNavigator: Navigator<OverviewViewState, OverviewController> {
        fun toSettings()
    }
}
