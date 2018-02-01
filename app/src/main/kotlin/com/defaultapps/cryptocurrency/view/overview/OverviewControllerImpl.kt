package com.defaultapps.cryptocurrency.view.overview

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import com.defaultapps.cryptocurrency.R
import com.defaultapps.cryptocurrency.utils.extensions.toUnit
import com.defaultapps.cryptocurrency.view.base.BaseController
import com.defaultapps.cryptocurrency.view.overview.OverviewAdapter.CurrencyListener
import com.defaultapps.cryptocurrency.view.overview.OverviewContract.OverviewPresenter
import com.defaultapps.cryptocurrency.view.overview.OverviewContract.OverviewController
import com.defaultapps.cryptocurrency.view.overview.OverviewContract.OverviewNavigator
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import kotlinx.android.synthetic.main.controller_overview.view.*
import kotlinx.android.synthetic.main.view_error.view.*
import kotlinx.android.synthetic.main.view_progress.view.*
import timber.log.Timber
import javax.inject.Inject

class OverviewControllerImpl :
        BaseController<OverviewViewState, OverviewController>(), OverviewController, CurrencyListener {

    @Inject lateinit var overviewNavigator: OverviewNavigator
    @Inject lateinit var overviewPresenter: OverviewPresenter
    @Inject lateinit var overviewAdapter: OverviewAdapter

    private val viewCompositeDisposable = CompositeDisposable()

    override fun inject() = screenComponent.inject(this)
    override fun provideLayout() = R.layout.controller_overview
    override fun providePresenter() = overviewPresenter
    override fun provideNavigator() = overviewNavigator

    override fun onViewCreated(view: View) {
        initAdapter(view.currencyRecycler)
        initToolbar(view.toolbar)
    }

    override fun onDestroyView(view: View) {
        super.onDestroyView(view)
        cleanup(view)
    }

    override fun retryAction(): Observable<Unit> =
            RxView.clicks(safeView!!.errorContainer.errorButton)
                    .doOnSubscribe { viewCompositeDisposable += it }
                    .toUnit()

    override fun initialLoad(): Observable<Unit> =
            Observable.just(Unit)

    override fun render(viewState: OverviewViewState) {
        when (viewState) {
            is OverviewViewState.DataState -> renderResult(viewState)
            is OverviewViewState.ErrorState -> renderError(viewState)
            is OverviewViewState.LoadingState -> renderLoading()
        }
    }

    override fun onCurrencyClick(id: String) {
        overviewNavigator.toDetail(id)
    }

    private fun renderLoading() {
        hideContent()
        hideErrorView()
        showLoading()
        Timber.d("Loading state")
    }

    private fun renderResult(viewState: OverviewViewState.DataState) {
        hideLoading()
        hideErrorView()
        showContent()
        bindContentToView(viewState)
        Timber.d("Data state")
    }

    private fun renderError(viewState: OverviewViewState.ErrorState) {
        hideLoading()
        hideContent()
        showErrorView()
        Timber.d(viewState.throwable)
    }

    private fun hideContent() {
        safeView!!.currencyRecycler.visibility = GONE
    }

    private fun showContent() {
        safeView!!.currencyRecycler.visibility = VISIBLE
    }

    private fun hideLoading() {
        safeView!!.progressBar.visibility = GONE
    }

    private fun showLoading() {
        safeView!!.progressBar.visibility = VISIBLE
    }

    private fun hideErrorView() {
        safeView!!.errorContainer.visibility = GONE
    }

    private fun showErrorView() {
        safeView!!.errorContainer.visibility = VISIBLE
    }

    private fun bindContentToView(viewState: OverviewViewState.DataState) {
        overviewAdapter.setData(viewState.currencyResponseList)
    }

    private fun initAdapter(currencyRecycler: RecyclerView) {
        currencyRecycler.layoutManager = LinearLayoutManager(applicationContext)
        currencyRecycler.adapter = overviewAdapter
        overviewAdapter.setCurrencyListener(this)
    }

    private fun cleanup(view: View) {
        view.currencyRecycler.adapter = null
        viewCompositeDisposable.clear()
    }

    private fun initToolbar(toolbar: Toolbar) {
        toolbar.inflateMenu(R.menu.overview_menu)
        val menu = toolbar.menu
        menu.findItem(R.id.actionSettings).setOnMenuItemClickListener {
            overviewNavigator.toSettings()
            return@setOnMenuItemClickListener true
        }
    }

}
