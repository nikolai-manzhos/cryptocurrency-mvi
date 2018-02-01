package com.defaultapps.cryptocurrency.view.detail

import com.defaultapps.cryptocurrency.R
import com.defaultapps.cryptocurrency.view.base.BaseController
import com.defaultapps.cryptocurrency.view.base.MviView
import com.defaultapps.cryptocurrency.view.detail.DetailContract.DetailController
import io.reactivex.Observable

class DetailControllerImpl :
        BaseController<DetailViewState, MviView<DetailViewState>>(), DetailController {

    override fun initialLoad(): Observable<Unit> =
            Observable.just(Unit)

    override fun provideLayout(): Int = R.layout.controller_detail
}
