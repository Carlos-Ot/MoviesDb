package com.arctouch.codechallenge.data.source.local

import com.arctouch.codechallenge.data.model.Movie
import com.arctouch.codechallenge.data.model.UpcomingMoviesResponse
import com.arctouch.codechallenge.data.source.dataSource.MovieDataSource
import io.reactivex.Observable


class MovieLocalDataSource: MovieDataSource {
    override fun getUpcommingMovies(page: Long): Observable<UpcomingMoviesResponse> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAndSaveMovie(movieId: Int): Observable<Movie> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}