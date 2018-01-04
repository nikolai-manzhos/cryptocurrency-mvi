package com.defaultapps.cryptocurrency.view.base

import android.support.annotation.CallSuper
import com.defaultapps.cryptocurrency.domain.base.UseCase
import io.reactivex.disposables.CompositeDisposable

abstract class BasePresenter<in VS: ViewState, V: MviView<VS>>
        (private vararg val useCases: UseCase = emptyArray()): MviPresenter<VS, V> {

    protected var view: V? = null
    protected val compositeDisposable = CompositeDisposable()
    protected var isAttachedFirstTime = true

    @CallSuper
    override fun onAttach(view: V) {
        this.view = view
        bindIntents()
        if (isAttachedFirstTime) isAttachedFirstTime = false
    }

    @CallSuper
    override fun onDetach() {
        compositeDisposable.clear()
        view = null
    }

    override fun disposeUseCaseCalls() {
        for (useCase in useCases) {
            useCase.dispose()
        }
    }

    abstract fun bindIntents()
}
