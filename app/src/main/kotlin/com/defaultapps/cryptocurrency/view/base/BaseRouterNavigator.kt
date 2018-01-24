package com.defaultapps.cryptocurrency.view.base

import com.bluelinelabs.conductor.Router

open class BaseRouterNavigator<in VS: ViewState, V: MviView<VS>>: BaseNavigator<VS, V>() {
    protected fun getRouter(): Router = (view?.activity() as BaseRouterActivity).router
}