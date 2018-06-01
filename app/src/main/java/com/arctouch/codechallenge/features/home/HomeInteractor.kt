package com.arctouch.codechallenge.features.home

import com.arctouch.codechallenge.data.model.UpcomingMoviesResponse
import com.arctouch.codechallenge.data.repositories.MovieRepository
import io.reactivex.Observable


class HomeInteractor(private val movieRepository: MovieRepository) {

    fun getUpcommingMovies(page: Long): Observable<UpcomingMoviesResponse> {
        return movieRepository.getUpcommingMovies(page)
    }
}