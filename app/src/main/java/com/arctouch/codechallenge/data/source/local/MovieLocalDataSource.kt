package com.arctouch.codechallenge.data.source.local

import com.arctouch.codechallenge.data.model.Movie
import com.arctouch.codechallenge.data.model.UpcomingMoviesResponse
import com.arctouch.codechallenge.data.source.dataSource.MovieDataSource
import io.reactivex.Observable


class MovieLocalDataSource: MovieDataSource {
    override fun getUpcommingMovies(): Observable<UpcomingMoviesResponse> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAndSaveMovie(): Observable<Movie> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}