package com.defaultapps.cryptocurrency.view.overview

import com.defaultapps.cryptocurrency.domain.model.Currency
import com.defaultapps.cryptocurrency.view.base.ViewState

sealed class OverviewViewState : ViewState {
    class LoadingState : OverviewViewState()
    data class ErrorState(val throwable: Throwable) : OverviewViewState()
    data class DataState(val currencyResponseList: List<Currency>) : OverviewViewState()
}
