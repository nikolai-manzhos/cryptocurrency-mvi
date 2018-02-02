package com.defaultapps.cryptocurrency.domain.usecase

import com.defaultapps.cryptocurrency.data.listener.OnPreferenceChange
import com.defaultapps.cryptocurrency.data.repository.NetworkRepository
import com.defaultapps.cryptocurrency.data.repository.PreferenceRepository
import com.defaultapps.cryptocurrency.domain.base.BaseUseCase
import com.defaultapps.cryptocurrency.injection.scope.PerScreen
import com.defaultapps.cryptocurrency.view.overview.OverviewViewState
import io.reactivex.Observable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

@PerScreen
class OverviewUseCaseImpl
@Inject constructor(private val networkRepository: NetworkRepository,
                    private val preferenceRepository: PreferenceRepository)
    : BaseUseCase(), OverviewUseCase, OnPreferenceChange {

    private var overviewBehaviourSubject: BehaviorSubject<OverviewViewState>? = null

    init {
        preferenceRepository.setChangeListener(this)
    }

    override fun dispose() {
        super.dispose()
        preferenceRepository.removeChangeListener(this)
    }

    override fun loadAllCryptocurrencies(force: Boolean): Observable<OverviewViewState> {
        if (force && overviewBehaviourSubject != null) {
            overviewBehaviourSubject = null
        }

        if (overviewBehaviourSubject == null) {
            overviewBehaviourSubject = BehaviorSubject.create()

            networkRepository.getAllCryptocurrencies(preferenceRepository.getMoneyType().name)
                    .doOnSubscribe { compositeDisposable.add(it) }
                    .map { OverviewViewState.DataState(it) }
                    .cast(OverviewViewState::class.java)
                    .startWith(OverviewViewState.LoadingState)
                    .onErrorReturn { OverviewViewState.ErrorState(it) }
                    .subscribeBy(onNext = { overviewBehaviourSubject!!.onNext(it) },
                                 onError = { overviewBehaviourSubject!!.onNext(OverviewViewState.ErrorState(it))})
        }

        return overviewBehaviourSubject!!
    }

    override fun onPreferenceChange(key: String) {
        overviewBehaviourSubject = null
    }
}
