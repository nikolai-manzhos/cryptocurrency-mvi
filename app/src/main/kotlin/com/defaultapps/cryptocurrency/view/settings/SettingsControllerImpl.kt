package com.defaultapps.cryptocurrency.view.settings

import android.support.v7.widget.Toolbar
import android.view.View
import com.defaultapps.cryptocurrency.R
import com.defaultapps.cryptocurrency.view.base.BaseController
import com.defaultapps.cryptocurrency.view.base.MviView
import com.defaultapps.cryptocurrency.view.base.ViewState
import kotlinx.android.synthetic.main.controller_settings.view.*


class SettingsControllerImpl : BaseController<ViewState, MviView<ViewState>>() {
    override fun provideLayout(): Int = R.layout.controller_settings

    override fun onViewCreated(view: View) {
        initToolbar(view.toolbar)
    }

    private fun initToolbar(toolbar: Toolbar) {
        toolbar.setNavigationOnClickListener { router.popCurrentController() }
    }
}
