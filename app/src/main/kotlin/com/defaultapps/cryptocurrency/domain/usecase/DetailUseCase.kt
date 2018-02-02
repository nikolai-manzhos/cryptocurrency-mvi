package com.defaultapps.cryptocurrency.domain.usecase

import com.defaultapps.cryptocurrency.view.detail.DetailViewState
import io.reactivex.Observable


interface DetailUseCase {
    fun loadCurrencyDetail(force: Boolean, id: String): Observable<DetailViewState>
}
