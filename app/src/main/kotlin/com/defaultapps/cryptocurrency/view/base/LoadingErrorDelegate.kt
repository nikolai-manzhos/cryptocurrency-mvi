package com.defaultapps.cryptocurrency.view.base

import android.view.View
import kotlinx.android.synthetic.main.controller_overview.view.*
import kotlinx.android.synthetic.main.view_progress.view.*

class LoadingErrorDelegate(private val view: View) {

    fun hideLoading() {
        view.progressBar.visibility = View.GONE
    }

    fun hideErrorView() {
        view.errorContainer.visibility = View.GONE
    }

    fun showErrorView() {
        view.errorContainer.visibility = View.VISIBLE
    }

    fun renderLoading() {
        hideErrorView()
        showLoading()
    }

    fun renderError() {
        hideLoading()
        showErrorView()
    }

    fun renderResult() {
        hideLoading()
        hideErrorView()
    }

    private fun showLoading() {
        view.progressBar.visibility = View.VISIBLE
    }
}
