package com.arctouch.codechallenge.features.details

import com.arctouch.codechallenge.data.model.Movie
import com.arctouch.codechallenge.data.repositories.MovieRepository
import io.reactivex.Observable


class MovieDetailsInteractor(private val movieRepository: MovieRepository) {

    fun getMovieDetails(movieId: Long): Observable<Movie> {
        return movieRepository.getAndSaveMovie(movieId)
    }
}