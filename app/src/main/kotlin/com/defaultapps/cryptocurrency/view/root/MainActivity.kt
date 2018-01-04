package com.defaultapps.cryptocurrency.view.root

import android.os.Bundle
import com.bluelinelabs.conductor.RouterTransaction
import com.defaultapps.cryptocurrency.R
import com.defaultapps.cryptocurrency.view.base.BaseRouterActivity
import com.defaultapps.cryptocurrency.view.base.ViewState
import com.defaultapps.cryptocurrency.view.overview.OverviewControllerImpl

class MainActivity : BaseRouterActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!router.hasRootController()) {
            router.setRoot(RouterTransaction.with(OverviewControllerImpl()))
        }
    }
    override fun render(viewState: ViewState) {
    }

    override fun provideLayout() = R.layout.activity_main
}
