package com.defaultapps.cryptocurrency.utils.extensions

import io.reactivex.Observable

fun <T> Observable<T>.toUnit(): Observable<Unit> {
    return this.flatMap { Observable.just(Unit) }
}
