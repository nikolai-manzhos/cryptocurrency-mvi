package com.defaultapps.cryptocurrency.view.base


interface MviPresenter<in VS: ViewState, in V: MviView<VS>> {
    fun onAttach(view: V)
    fun onDetach()
    fun disposeUseCaseCalls()
}
