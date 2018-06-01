package com.arctouch.codechallenge.features.details

import android.util.Log
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
    }

    fun loadMovieDetails(movieId: Int) {
        interactor.getMovieDetails(movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    Log.d("carlosottoboni", "MovieTitle: ${it.title}")
                }
    }

}