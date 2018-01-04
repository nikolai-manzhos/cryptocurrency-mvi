package com.defaultapps.cryptocurrency.domain.usecase

import com.defaultapps.cryptocurrency.view.overview.OverviewViewState
import io.reactivex.Observable

interface OverviewUseCase {
    fun loadAllCryptocurrencies(force: Boolean): Observable<OverviewViewState>
}