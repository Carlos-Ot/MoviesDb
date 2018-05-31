package com.arctouch.codechallenge.features.home

import com.arctouch.codechallenge.base.BaseView
import com.arctouch.codechallenge.data.model.Movie


interface HomeView: BaseView {

    fun showMovies(movies: MutableList<Movie>)
    fun showNextPage(movies: MutableList<Movie>)
}