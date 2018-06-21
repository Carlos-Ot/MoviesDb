package com.arctouch.codechallenge.data.source.remote

import com.arctouch.codechallenge.data.Cache
import com.arctouch.codechallenge.data.model.GenreResponse
import com.arctouch.codechallenge.data.model.Movie
import com.arctouch.codechallenge.data.model.UpcomingMoviesResponse
import com.arctouch.codechallenge.data.source.dataSource.MovieDataSource
import com.arctouch.codechallenge.data.source.remote.network.TmdbApi
import io.reactivex.Observable
import io.reactivex.functions.BiFunction


class MovieRemoteDataSource(private val apiClient: TmdbApi): MovieDataSource {

    override fun getUpcommingMovies(page: Long): Observable<UpcomingMoviesResponse> {
       return Observable.zip(
               apiClient.genres()
                       .flatMap {
                           Cache.cacheGenres(it.genres)
                           Observable.just(it)
                       },
               apiClient.upcomingMovies(page, TmdbApi.DEFAULT_REGION),
                  BiFunction<GenreResponse, UpcomingMoviesResponse, UpcomingMoviesResponse> {
                      _: GenreResponse, movies: UpcomingMoviesResponse ->
                      movies
               })
    }

    override fun getAndSaveMovie(movieId: Long): Observable<Movie> {
        return apiClient.movie(movieId)
    }
}