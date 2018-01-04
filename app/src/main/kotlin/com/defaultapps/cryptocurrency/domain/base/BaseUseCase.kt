package com.defaultapps.cryptocurrency.domain.base

import io.reactivex.disposables.CompositeDisposable

abstract class BaseUseCase: UseCase {

    protected val compositeDisposable = CompositeDisposable()

    override fun dispose() {
        compositeDisposable.clear()
    }
}
