package com.arctouch.codechallenge.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.subscribers.DisposableSubscriber


abstract class BaseInteractor<T> {

    protected var disposables: CompositeDisposable = CompositeDisposable()

    protected fun getDisposableSingleObserver(
            onNext: (T) -> Unit,
            onError: (Throwable) -> Unit = {}): DisposableSingleObserver<T> {
        return object : DisposableSingleObserver<T>() {
            override fun onSuccess(t: T) {
                onNext.invoke(t)
            }

            override fun onError(e: Throwable) {
                onError.invoke(e)
            }
        }
    }

    protected fun getDisposableCompletableObserver(
            onComplete: () -> Unit,
            onError: (Throwable) -> Unit = {}): DisposableCompletableObserver {
        return object : DisposableCompletableObserver() {
            override fun onComplete() {
                onComplete.invoke()
            }

            override fun onError(e: Throwable) {
                onError.invoke(e)
            }
        }
    }

    protected fun getDisposableSubscriber(
            onNext: (T) -> Unit,
            onError: (Throwable) -> Unit = {}): DisposableSubscriber<T> {
        return object : DisposableSubscriber<T>() {
            override fun onNext(t: T) {
                onNext.invoke(t)
            }

            override fun onComplete() {
            }

            override fun onError(t: Throwable) {
                onError.invoke(t)
            }
        }
    }

    fun dispose() {
        disposables.clear()
    }

}