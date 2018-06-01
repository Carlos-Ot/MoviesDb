package com.arctouch.codechallenge.data.source.dataSource

import com.arctouch.codechallenge.data.model.Movie
import com.arctouch.codechallenge.data.model.UpcomingMoviesResponse
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single


interface MovieDataSource {

    fun getUpcommingMovies(page: Long): Observable<UpcomingMoviesResponse>

    fun getAndSaveMovie(): Observable<Movie>
}