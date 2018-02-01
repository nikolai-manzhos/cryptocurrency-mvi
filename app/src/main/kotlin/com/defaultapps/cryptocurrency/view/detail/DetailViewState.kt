package com.defaultapps.cryptocurrency.view.detail

import com.defaultapps.cryptocurrency.domain.model.Currency
import com.defaultapps.cryptocurrency.view.base.ViewState


sealed class DetailViewState : ViewState {
    object LoadingState : DetailViewState()
    data class DataState(val currency: Currency) : DetailViewState()
    data class ErrorState(val throwable: Throwable) : DetailViewState()
}
