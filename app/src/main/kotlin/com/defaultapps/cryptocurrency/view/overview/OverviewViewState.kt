package com.defaultapps.cryptocurrency.view.overview

import com.defaultapps.cryptocurrency.data.entity.Currency
import com.defaultapps.cryptocurrency.view.base.ViewState

interface OverviewViewState: ViewState {

    class LoadingState: OverviewViewState

    class ErrorState(val throwable: Throwable): OverviewViewState

    class DataState(val currencyList: List<Currency>): OverviewViewState
}