package com.defaultapps.cryptocurrency.view.detail

import com.defaultapps.cryptocurrency.view.base.MviPresenter
import com.defaultapps.cryptocurrency.view.base.MviView

interface DetailContract {
    interface DetailController : MviView<DetailViewState>
    interface DetailPresenter : MviPresenter<DetailViewState, DetailController>
}
