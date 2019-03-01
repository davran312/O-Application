package sample.test.utils

import io.reactivex.Observer
import io.reactivex.disposables.Disposable

abstract class Observer<T>:Observer<T>{

    override fun onComplete() {

    }

    override fun onSubscribe(d: Disposable) {

    }

    override fun onNext(t:T) {
        next(t)

    }

    override fun onError(e: Throwable) {
        error(e)
    }

    abstract fun next(t:T)
    abstract fun error(e:Throwable)
}

