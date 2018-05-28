package com.arctouch.codechallenge.features.home

import com.arctouch.codechallenge.data.model.UpcomingMoviesResponse
import com.arctouch.codechallenge.data.repositories.MovieRepository
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers


class HomeInteractor(private val movieRepository: MovieRepository) {

    fun getUpcommingMovies(): Observable<UpcomingMoviesResponse> {
        return movieRepository.getUpcommingMovies()
    }
}