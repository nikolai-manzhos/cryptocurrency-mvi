package com.defaultapps.cryptocurrency.view.detail

import com.defaultapps.cryptocurrency.injection.scope.PerScreen
import com.defaultapps.cryptocurrency.view.base.BasePresenter
import javax.inject.Inject

@PerScreen
class DetailPresenterImpl @Inject constructor() :
        BasePresenter<DetailViewState, DetailContract.DetailController>() {

    override fun bindIntents() {
    }
}
