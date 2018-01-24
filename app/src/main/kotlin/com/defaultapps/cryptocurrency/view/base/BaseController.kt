package com.defaultapps.cryptocurrency.view.base

import android.content.Context
import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bluelinelabs.conductor.Controller
import com.defaultapps.cryptocurrency.App
import com.defaultapps.cryptocurrency.injection.component.ScreenComponent

abstract class BaseController<in VS: ViewState, in V: MviView<VS>>(args: Bundle? = null) : Controller(args), MviView<VS> {

    private var presenter: MviPresenter<VS, V>? = null
    private var navigator: Navigator<VS, V>? = null

    protected lateinit var screenComponent: ScreenComponent
        private set
    private var isInjected: Boolean = false

    protected var safeView: View? = null

    override fun onContextAvailable(context: Context) {
        if (isInjected) {
            return
        }
        val app = context.applicationContext as App
        screenComponent = app.applicationComponent.plusScreenComponent()
        inject()
        presenter = providePresenter()
        navigator = provideNavigator()
        isInjected = true
    }

    @Suppress("UNCHECKED_CAST")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(provideLayout(), container, false)
        safeView = view
        presenter?.onAttach(this as V)
        navigator?.onAttach(this as V)
        onViewCreated(view)
        return view
    }

    open fun onViewCreated(view: View) {}

    @CallSuper
    override fun onDestroyView(view: View) {
        presenter?.onDetach()
        navigator?.onDetach()
        safeView = null
    }

    @CallSuper
    override fun onDestroy() {
        presenter?.disposeUseCaseCalls()
    }

    @LayoutRes
    abstract fun provideLayout(): Int

    open fun providePresenter(): MviPresenter<VS, V>? = null

    open fun provideNavigator(): Navigator<VS, V>? = null

    override fun activity() = activity as BaseActivity

    override fun render(viewState: VS) {}

    open fun inject() {}
}