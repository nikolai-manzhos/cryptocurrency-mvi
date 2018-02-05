package com.defaultapps.cryptocurrency.view.detail

import android.os.Bundle
import android.support.v4.view.ViewCompat
import android.support.v7.widget.Toolbar
import android.view.View
import com.defaultapps.cryptocurrency.R
import com.defaultapps.cryptocurrency.utils.Constants
import com.defaultapps.cryptocurrency.utils.ResUtils
import com.defaultapps.cryptocurrency.utils.extensions.loadSimple
import com.defaultapps.cryptocurrency.view.base.BaseController
import com.defaultapps.cryptocurrency.view.base.LoadingErrorDelegate
import com.defaultapps.cryptocurrency.view.detail.DetailContract.DetailController
import com.defaultapps.cryptocurrency.view.detail.DetailContract.DetailPresenter
import io.reactivex.Observable
import kotlinx.android.synthetic.main.controller_detail.view.*
import timber.log.Timber
import javax.inject.Inject

class DetailControllerImpl(bundle: Bundle) :
        BaseController<DetailViewState, DetailController>(bundle), DetailController {

    @Inject lateinit var presenter: DetailPresenter
    @Inject lateinit var resUtils: ResUtils

    private var viewDelegate: LoadingErrorDelegate? = null

    override fun inject() = screenComponent.inject(this)
    override fun provideLayout(): Int = R.layout.controller_detail
    override fun providePresenter() = presenter

    override fun onViewCreated(view: View) {
        initToolbar(view.detailToolbar)
        initViewDelegate(view)
        initTransitionAnimation(view)
    }

    override fun initialLoad(): Observable<String> =
            Observable.just(args.getString(Constants.EXTRA_CONTENT))

    override fun render(viewState: DetailViewState) {
        when(viewState) {
            DetailViewState.LoadingState -> renderLoading()
            is DetailViewState.DataState -> renderResult(viewState)
            is DetailViewState.ErrorState -> renderError(viewState)
        }
    }

    private fun renderLoading() {
        viewDelegate?.renderLoading()
        hideContent()
    }

    private fun renderError(viewState: DetailViewState.ErrorState) {
        viewDelegate?.renderError()
        hideContent()
        Timber.d(viewState.throwable)
    }

    private fun renderResult(viewState: DetailViewState.DataState) {
        viewDelegate?.renderResult()
        showContent()
        bindContentToView(viewState)
    }

    private fun hideContent() {
        safeView!!.detailContent.visibility = View.GONE
    }

    private fun showContent() {
        safeView!!.detailContent.visibility = View.VISIBLE
    }

    private fun bindContentToView(viewState: DetailViewState.DataState) {
        val currency = viewState.currency
        val view = safeView!!
        view.name.text = currency.name
        view.icon.loadSimple(Constants.IMAGE_BASE_URL + currency.id + Constants.IMAGE_FORMAT)
        view.hourChange.text = currency.percentChange1h.toString()
        view.dayChange.text = currency.percentChange24.toString()
        view.weekChange.text = currency.percentChange7d.toString()
    }

    private fun initTransitionAnimation(view: View) {
        val position = args.getInt(Constants.EXTRA_POSITION)
        ViewCompat.setTransitionName(view.icon,
                resUtils.getString(R.string.transition_tag_image_indexed, position))
        ViewCompat.setTransitionName(view.name,
                resUtils.getString(R.string.transition_tag_name_indexed, position))
    }

    private fun initViewDelegate(view: View) {
        viewDelegate = LoadingErrorDelegate(view)
    }

    private fun initToolbar(toolbar: Toolbar) {
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp)
        toolbar.setNavigationOnClickListener { router.popCurrentController() }
    }

}
