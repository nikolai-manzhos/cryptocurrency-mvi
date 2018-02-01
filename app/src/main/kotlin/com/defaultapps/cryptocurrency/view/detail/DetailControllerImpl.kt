package com.defaultapps.cryptocurrency.view.detail

import com.defaultapps.cryptocurrency.R
import com.defaultapps.cryptocurrency.view.base.BaseController
import com.defaultapps.cryptocurrency.view.base.MviView

class DetailControllerImpl : BaseController<DetailViewState, MviView<DetailViewState>>() {
    override fun provideLayout(): Int = R.layout.controller_detail
}