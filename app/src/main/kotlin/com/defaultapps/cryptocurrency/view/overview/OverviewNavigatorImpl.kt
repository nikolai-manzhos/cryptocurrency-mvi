package com.defaultapps.cryptocurrency.view.overview

import android.os.Bundle
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler
import com.defaultapps.cryptocurrency.R
import com.defaultapps.cryptocurrency.injection.scope.PerScreen
import com.defaultapps.cryptocurrency.utils.Constants
import com.defaultapps.cryptocurrency.utils.ResUtils
import com.defaultapps.cryptocurrency.utils.changehandler.ArcFadeMoveChangeHandlerCompat
import com.defaultapps.cryptocurrency.view.base.BaseRouterNavigator
import com.defaultapps.cryptocurrency.view.detail.DetailControllerImpl
import com.defaultapps.cryptocurrency.view.overview.OverviewContract.OverviewController
import com.defaultapps.cryptocurrency.view.overview.OverviewContract.OverviewNavigator
import com.defaultapps.cryptocurrency.view.settings.SettingsControllerImpl
import javax.inject.Inject

@PerScreen
class OverviewNavigatorImpl @Inject constructor(private val resUtils: ResUtils)
    : BaseRouterNavigator<OverviewViewState, OverviewController>(), OverviewNavigator {
    override fun toDetail(id: String, position: Int) {
        val bundle = Bundle()
        bundle.putString(Constants.EXTRA_CONTENT, id)
        bundle.putInt(Constants.EXTRA_POSITION, position)

        val iconSharedElement = resUtils.getString(R.string.transition_tag_image_indexed, position)
        val nameSharedElement = resUtils.getString(R.string.transition_tag_name_indexed, position)

        getRouter().pushController(RouterTransaction.with(
                DetailControllerImpl(bundle))
                .pushChangeHandler(FadeChangeHandler())
                .popChangeHandler(ArcFadeMoveChangeHandlerCompat(nameSharedElement, iconSharedElement)))
    }

    override fun toSettings() {
        getRouter().pushController(RouterTransaction.with(
                SettingsControllerImpl())
                .popChangeHandler(FadeChangeHandler())
                .pushChangeHandler(FadeChangeHandler()))
    }
}
