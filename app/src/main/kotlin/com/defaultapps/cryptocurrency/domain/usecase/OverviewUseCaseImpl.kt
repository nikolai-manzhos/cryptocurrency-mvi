package com.defaultapps.cryptocurrency.domain.usecase

import com.defaultapps.cryptocurrency.data.repository.NetworkRepository
import com.defaultapps.cryptocurrency.domain.base.BaseUseCase
import com.defaultapps.cryptocurrency.injection.scope.PerScreen
import com.defaultapps.cryptocurrency.view.overview.OverviewViewState
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

@PerScreen
class OverviewUseCaseImpl
@Inject constructor(private val networkRepository: NetworkRepository): BaseUseCase(), OverviewUseCase {

    private var overviewBehaviourSubject: BehaviorSubject<OverviewViewState>? = null

    override fun loadAllCryptocurrencies(force: Boolean): Observable<OverviewViewState> {
        if (force && overviewBehaviourSubject!= null) {
            overviewBehaviourSubject = null
        }

        if (overviewBehaviourSubject == null) {
            overviewBehaviourSubject = BehaviorSubject.create()

            networkRepository.getAllCryptocurrencies()
                    .doOnSubscribe { compositeDisposable.add(it) }
                    .toObservable()
                    .map { OverviewViewState.DataState(it) }
                    .cast(OverviewViewState::class.java)
                    .startWith(OverviewViewState.LoadingState())
                    .onErrorReturn { OverviewViewState.ErrorState(it) }
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribeBy { viewState -> overviewBehaviourSubject?.onNext(viewState) }
        }

        return overviewBehaviourSubject!!
    }
}