package com.defaultapps.cryptocurrency.view.base


abstract class BaseNavigator<in VS: ViewState, V: MviView<VS>> : Navigator<VS, V> {

    protected var view: V? = null
        private set

    override fun onAttach(v: V) {
        this.view = v
    }

    override fun onDetach() {
        view = null
    }

    protected fun getActivity(): BaseActivity = view!!.activity()

}