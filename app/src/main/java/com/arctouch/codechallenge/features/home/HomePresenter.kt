package com.arctouch.codechallenge.features.home

import com.arctouch.codechallenge.base.BasePresenter
import com.arctouch.codechallenge.data.Cache
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class HomePresenter(private val interactor: HomeInteractor): BasePresenter<HomeView>() {

    override fun init() {
        interactor.getUpcommingMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    val moviesWithGenres = it.results.map { movie ->
                        movie.copy(genres = Cache.genres.filter { movie.genreIds?.contains(it.id) == true })
                    }

                    view?.showMovies(moviesWithGenres)
                }
    }

    override fun resume() {
    }

    override fun pause() {
    }

    override fun stop() {
    }

    override fun destroy() {
        //TODO Call dispose here
    }
}