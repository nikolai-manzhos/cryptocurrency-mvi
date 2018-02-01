package com.defaultapps.cryptocurrency.view.detail

import com.defaultapps.cryptocurrency.view.base.MviPresenter
import com.defaultapps.cryptocurrency.view.base.MviView

class DetailContract {
    interface DetailController : MviView<DetailViewState>
    interface DetailPresenter : MviPresenter<DetailViewState, DetailController>
}
