package com.defaultapps.cryptocurrency.view.base

interface Navigator<in VS:ViewState, in View : MviView<VS>> {
    fun onAttach(v: View)
    fun onDetach()
    fun finishActivity()
}
