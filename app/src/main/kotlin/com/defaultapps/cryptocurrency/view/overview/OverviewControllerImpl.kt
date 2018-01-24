package com.defaultapps.cryptocurrency.view.overview

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler
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

    override fun inject() = screenComponent.inject(this)

    override fun provideLayout() = R.layout.controller_overview

    override fun providePresenter() = overviewPresenter

    override fun onViewCreated(view: View) {
        initAdapter(view)
        initToolbar(view)
    }

    override fun onDestroyView(view: View) {
        super.onDestroyView(view)
        view.currencyRecycler.adapter = null
        viewCompositeDisposable.clear()
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
        safeView!!.loadingContainer.progressBar.visibility = GONE
    }

    private fun showLoading() {
        safeView!!.loadingContainer.progressBar.visibility = VISIBLE
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

    private fun initAdapter(view: View) {
        view.currencyRecycler.layoutManager = LinearLayoutManager(applicationContext)
        view.currencyRecycler.adapter = overviewAdapter
    }

    private fun initToolbar(view: View) {
        view.toolbar.inflateMenu(R.menu.overview_menu)
        val menu = view.toolbar.menu
        menu.findItem(R.id.actionSettings).setOnMenuItemClickListener {
            return@setOnMenuItemClickListener true
        }
    }

}
