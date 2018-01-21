package com.defaultapps.cryptocurrency.view.overview

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.view.View.*
import com.defaultapps.cryptocurrency.R
import com.defaultapps.cryptocurrency.view.base.BaseController
import com.defaultapps.cryptocurrency.view.overview.OverviewContract.OverviewController
import com.defaultapps.cryptocurrency.view.overview.OverviewContract.OverviewPresenter
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import kotlinx.android.synthetic.main.controller_overview.view.*
import kotlinx.android.synthetic.main.view_error.view.*
import kotlinx.android.synthetic.main.view_progress.view.*
import timber.log.Timber
import javax.inject.Inject

class OverviewControllerImpl : BaseController<OverviewViewState, OverviewController>(), OverviewController {

    @Inject lateinit var overviewPresenter: OverviewPresenter
    @Inject lateinit var overviewAdapter: OverviewAdapter

    private val viewCompositeDisposable = CompositeDisposable()

    override fun onViewCreated(view: View) {
        view.currencyRecycler.layoutManager = LinearLayoutManager(applicationContext)
        view.currencyRecycler.adapter = overviewAdapter
    }

    override fun onDestroyView(view: View) {
        view.currencyRecycler.adapter = null
        viewCompositeDisposable.clear()
        super.onDestroyView(view)
    }

    override fun retryAction(): Observable<Boolean> =
            RxView.clicks(safeView!!.errorContainer.errorButton)
                    .doOnSubscribe { viewCompositeDisposable += it }
                    .map { true }

    override fun loadData(): Observable<Boolean> = Observable.just(true)

    override fun render(viewState: OverviewViewState) {
        when (viewState) {
            is OverviewViewState.LoadingState -> renderLoading()
            is OverviewViewState.DataState -> renderResult(viewState)
            is OverviewViewState.ErrorState -> renderError(viewState)
        }
    }

    private fun renderLoading() {
        safeView!!.currencyRecycler.visibility = GONE
        safeView!!.loadingContainer.progressBar.visibility = VISIBLE
        hideErrorView()
        Timber.d("Loading state")
    }

    private fun renderResult(viewState: OverviewViewState.DataState) {
        safeView!!.loadingContainer.progressBar.visibility = GONE
        safeView!!.currencyRecycler.visibility = VISIBLE
        hideErrorView()
        overviewAdapter.setData(viewState.currencyResponseList)
        Timber.d("Data state")
    }

    private fun renderError(viewState: OverviewViewState.ErrorState) {
        safeView!!.loadingContainer.progressBar.visibility = GONE
        safeView!!.currencyRecycler.visibility = GONE
        safeView!!.errorContainer.visibility = VISIBLE
        showErrorView()
        Timber.d(viewState.throwable)
    }

    private fun hideErrorView() {
        safeView!!.errorContainer.visibility = GONE
    }

    private fun showErrorView() {
        safeView!!.errorContainer.visibility = VISIBLE
    }

    override fun inject() = screenComponent.inject(this)

    override fun provideLayout() = R.layout.controller_overview

    override fun providePresenter() = overviewPresenter

}
