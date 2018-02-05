package com.defaultapps.cryptocurrency.domain.usecase

import com.defaultapps.cryptocurrency.data.repository.NetworkRepository
import com.defaultapps.cryptocurrency.data.repository.PreferenceRepository
import com.defaultapps.cryptocurrency.domain.base.BaseUseCase
import com.defaultapps.cryptocurrency.injection.scope.PerScreen
import com.defaultapps.cryptocurrency.view.detail.DetailViewState
import io.reactivex.Observable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

@PerScreen
class DetailUseCaseImpl @Inject constructor(
        private val networkRepository: NetworkRepository,
        private val preferenceRepository: PreferenceRepository)
    : BaseUseCase(), DetailUseCase {

    private var currencyDetailSubject: BehaviorSubject<DetailViewState>? = null

    override fun loadCurrencyDetail(force: Boolean, id: String): Observable<DetailViewState> {
        if (force && currencyDetailSubject != null) {
            currencyDetailSubject = null
        }

        if (currencyDetailSubject == null) {
            currencyDetailSubject = BehaviorSubject.create()

            networkRepository.getCryptocurrencyDetail(id, preferenceRepository.getMoneyType().toString())
                    .doOnSubscribe { compositeDisposable.add(it) }
                    .map { DetailViewState.DataState(it) }
                    .cast(DetailViewState::class.java)
                    .startWith(DetailViewState.LoadingState)
                    .onErrorReturn { DetailViewState.ErrorState(it) }
                    .subscribeBy( onNext = {currencyDetailSubject!!.onNext(it)})
        }

        return currencyDetailSubject!!
    }
}
