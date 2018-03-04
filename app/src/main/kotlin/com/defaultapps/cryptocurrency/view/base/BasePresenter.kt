package com.defaultapps.cryptocurrency.view.base

import android.support.annotation.CallSuper
import com.defaultapps.cryptocurrency.domain.base.UseCase
import io.reactivex.disposables.CompositeDisposable

abstract class BasePresenter<in VS: ViewState, in V: MviView<VS>>
        (private vararg val useCases: UseCase = emptyArray()): MviPresenter<VS, V> {

    protected val compositeDisposable = CompositeDisposable()
    protected var isAttachedFirstTime = true

    @CallSuper
    override fun onAttach(view: V) {
        bindIntents(view)
        if (isAttachedFirstTime) isAttachedFirstTime = false
    }

    @CallSuper
    override fun onDetach() {
        compositeDisposable.clear()
    }

    override fun disposeUseCaseCalls() {
        for (useCase in useCases) {
            useCase.dispose()
        }
    }

    abstract fun bindIntents(view: V)
}
