package com.arctouch.codechallenge.features.details

import android.util.Log
import com.arctouch.codechallenge.R
import com.arctouch.codechallenge.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class MovieDetailsPresenter(private val interactor: MovieDetailsInteractor): BasePresenter<MovieDetailsView>() {

    override fun init() {
    }

    override fun resume() {
    }

    override fun pause() {
    }

    override fun stop() {
    }

    override fun destroy() {
        compositeDisposable.clear()
    }

    fun loadMovieDetails(movieId: Long) {
        val disposable = interactor.getMovieDetails(movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view?.showMovieDetail(it)
                },{
                    view?.showError(R.string.movieDetailsLoadingError)
                })
        compositeDisposable.add(disposable)
    }

}