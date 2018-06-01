package com.arctouch.codechallenge.features.details

import com.arctouch.codechallenge.base.BaseView
import com.arctouch.codechallenge.data.model.Movie


interface MovieDetailsView: BaseView {
    fun showMovieDetail(movie: Movie)
}