package com.arctouch.codechallenge.data.repositories

import com.arctouch.codechallenge.data.model.Movie
import com.arctouch.codechallenge.data.model.UpcomingMoviesResponse
import com.arctouch.codechallenge.data.source.dataSource.MovieDataSource
import io.reactivex.Observable


class MovieRepository(
         private val movieRemoteDataSource: MovieDataSource,
         private val movieLocalDataSource: MovieDataSource): MovieDataSource {

    override fun getUpcommingMovies(): Observable<UpcomingMoviesResponse> {
        return movieRemoteDataSource.getUpcommingMovies()
    }

    override fun getAndSaveMovie(): Observable<Movie> {
        return movieRemoteDataSource.getAndSaveMovie()
    }
}