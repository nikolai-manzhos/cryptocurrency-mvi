package com.defaultapps.cryptocurrency.view.overview

import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.defaultapps.cryptocurrency.R
import com.defaultapps.cryptocurrency.view.base.BaseController
import com.defaultapps.cryptocurrency.view.overview.OverviewContract.OverviewController
import com.defaultapps.cryptocurrency.view.overview.OverviewContract.OverviewPresenter
import io.reactivex.Observable
import kotlinx.android.synthetic.main.controller_overview.view.*
import javax.inject.Inject

class OverviewControllerImpl : BaseController<OverviewViewState, OverviewController>(), OverviewController {

    @Inject lateinit var overviewPresenter: OverviewPresenter
    private val overviewAdapter = OverviewAdapter()

    override fun onViewCreated(view: View) {
        view.currencyRecycler.layoutManager = LinearLayoutManager(applicationContext)
        view.currencyRecycler.adapter = overviewAdapter
    }

    override fun onDestroyView(view: View) {
        view.currencyRecycler.adapter = null
        super.onDestroyView(view)
    }

    override fun loadData(): Observable<Boolean> {
        return Observable.just(true)
    }

    override fun render(viewState: OverviewViewState) {
        when (viewState) {
            is OverviewViewState.LoadingState -> Log.d("Overview", "Loading state" )
            is OverviewViewState.DataState -> {
                overviewAdapter.setData(viewState.currencyList)
                Log.d("Overview", "Data state" )
            }
            is OverviewViewState.ErrorState -> Log.e("Overview", "Error state", viewState.throwable )
        }
    }

    override fun inject() = screenComponent.inject(this)

    override fun provideLayout() = R.layout.controller_overview

    override fun providePresenter() = overviewPresenter

}
