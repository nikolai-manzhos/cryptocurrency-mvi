package com.defaultapps.cryptocurrency.view.base

interface MviView<in T: ViewState> {
    fun render(viewState: T)
    fun provideActivity(): BaseActivity
}
