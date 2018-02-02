package com.defaultapps.cryptocurrency.view.base

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.controller_overview.view.*
import kotlinx.android.synthetic.main.view_progress.view.*
import timber.log.Timber

abstract class BaseLceController<in VS: ViewState, in V: MviView<VS>>
(args: Bundle? = null) : BaseController<VS, V>(args) {

    protected open fun hideLoading() {
        safeView!!.progressBar.visibility = View.GONE
    }

    protected open fun showLoading() {
        safeView!!.progressBar.visibility = View.VISIBLE
    }

    protected open fun hideErrorView() {
        safeView!!.errorContainer.visibility = View.GONE
    }

    protected open fun showErrorView() {
        safeView!!.errorContainer.visibility = View.VISIBLE
    }

    protected open fun hideContent() {}

    protected open fun showContent() {}

    protected open fun renderLoading() {
        hideContent()
        hideErrorView()
        showLoading()
        Timber.d("Loading state")
    }
}
