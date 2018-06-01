package com.arctouch.codechallenge.features.home

import com.arctouch.codechallenge.base.BasePresenter
import com.arctouch.codechallenge.data.Cache
import com.arctouch.codechallenge.data.model.Movie
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

private const val DEFAULT_PAGE = 1L

class HomePresenter(private val interactor: HomeInteractor): BasePresenter<HomeView>() {

    override fun init() {
        loadMovies(isFirst = true)
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

    fun loadMovies(page: Long = DEFAULT_PAGE, isFirst: Boolean = false) {
        interactor.getUpcommingMovies(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {

                    if (isFirst) {
                        view?.showMovies(it.first, it.second)
                    } else {
                        view?.showNextPage(it.first)
                    }
                }
    }

    fun handleItemCLicked(movieId: Long) {
        view?.callMovieDetailsActivity(movieId)
    }
}