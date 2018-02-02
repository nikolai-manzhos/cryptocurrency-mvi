package com.defaultapps.cryptocurrency.view.detail

import com.defaultapps.cryptocurrency.view.base.MviPresenter
import com.defaultapps.cryptocurrency.view.base.MviView
import io.reactivex.Observable

interface DetailContract {
    interface DetailController : MviView<DetailViewState> {
        fun initialLoad(): Observable<String>
    }
    interface DetailPresenter : MviPresenter<DetailViewState, DetailController>
}
