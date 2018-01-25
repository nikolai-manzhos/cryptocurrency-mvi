package com.defaultapps.cryptocurrency.view.detail

import com.defaultapps.cryptocurrency.view.base.MviPresenter
import com.defaultapps.cryptocurrency.view.base.MviView

class DetailContract {
    interface DetailView : MviView<DetailViewState>
    interface DetailPresetner : MviPresenter<DetailViewState, DetailView>
}