package com.defaultapps.cryptocurrency.view.overview

import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler
import com.defaultapps.cryptocurrency.injection.scope.PerScreen
import com.defaultapps.cryptocurrency.view.base.BaseRouterNavigator
import com.defaultapps.cryptocurrency.view.detail.DetailControllerImpl
import com.defaultapps.cryptocurrency.view.overview.OverviewContract.OverviewController
import com.defaultapps.cryptocurrency.view.overview.OverviewContract.OverviewNavigator
import com.defaultapps.cryptocurrency.view.settings.SettingsControllerImpl
import javax.inject.Inject

@PerScreen
class OverviewNavigatorImpl @Inject constructor()
    : BaseRouterNavigator<OverviewViewState, OverviewController>(), OverviewNavigator {
    override fun toDetail(id: String) {
        getRouter().pushController(RouterTransaction.with(
                DetailControllerImpl())
                .popChangeHandler(FadeChangeHandler())
                .pushChangeHandler(FadeChangeHandler()))
    }

    override fun toSettings() {
        getRouter().pushController(RouterTransaction.with(
                SettingsControllerImpl())
                .popChangeHandler(FadeChangeHandler())
                .pushChangeHandler(FadeChangeHandler()))
    }
}
