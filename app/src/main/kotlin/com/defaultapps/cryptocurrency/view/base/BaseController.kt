package com.defaultapps.cryptocurrency.view.base

import android.content.Context
import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import butterknife.Unbinder
import com.bluelinelabs.conductor.Controller
import com.defaultapps.cryptocurrency.DebugApp
import com.defaultapps.cryptocurrency.injection.component.ScreenComponent

abstract class BaseController<VS: ViewState, V: MviView<VS>>(args: Bundle? = null) : Controller(args), MviView<VS> {

    private var presenter: MviPresenter<VS, V>? = null
    private var navigator: Navigator<VS, V>? = null

    protected lateinit var screenComponent: ScreenComponent
    private set
    private lateinit var unbinder: Unbinder

    private var isInjected: Boolean = false

    override fun onContextAvailable(context: Context) {
        if (isInjected) {
            return
        }
        val app = context.applicationContext as DebugApp
        screenComponent = app.applicationComponent.plusScreenComponent()
        inject()
        presenter = providePresenter()
        navigator = provideNavigator()
        isInjected = true
    }

    @Suppress("UNCHECKED_CAST")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(provideLayout(), container, false)
        unbinder = ButterKnife.bind(this, view)
        presenter?.onAttach(this as V)
        navigator?.onAttach(this as V)
        onViewCreated(view)
        return view
    }

    open fun onViewCreated(view: View) {}

    @CallSuper
    override fun onDestroyView(view: View) {
        unbinder.unbind()
    }

    @CallSuper
    override fun onDestroy() {
        presenter?.disposeUseCaseCalls()
    }

    @LayoutRes
    abstract fun provideLayout(): Int

    open fun providePresenter(): MviPresenter<VS, V>? = null

    open fun provideNavigator(): Navigator<VS, V>? = null

    override fun provideActivity() = activity as BaseActivity

    open fun inject() {}
}