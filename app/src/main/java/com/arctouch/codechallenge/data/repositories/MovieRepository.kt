package com.arctouch.codechallenge.data.repositories

import com.arctouch.codechallenge.data.model.Movie
import com.arctouch.codechallenge.data.model.UpcomingMoviesResponse
import com.arctouch.codechallenge.data.source.dataSource.MovieDataSource
import io.reactivex.Observable


class MovieRepository(
         private val movieRemoteDataSource: MovieDataSource,
         private val movieLocalDataSource: MovieDataSource): MovieDataSource {

    override fun getUpcommingMovies(page: Long): Observable<UpcomingMoviesResponse> {
        return movieRemoteDataSource.getUpcommingMovies(page)
    }

    override fun getAndSaveMovie(movieId: Int): Observable<Movie> {
        return movieRemoteDataSource.getAndSaveMovie(movieId)
    }
}