package com.arctouch.codechallenge.data.source.remote

import com.arctouch.codechallenge.data.Cache
import com.arctouch.codechallenge.data.model.GenreResponse
import com.arctouch.codechallenge.data.model.Movie
import com.arctouch.codechallenge.data.model.UpcomingMoviesResponse
import com.arctouch.codechallenge.data.source.dataSource.MovieDataSource
import com.arctouch.codechallenge.data.source.remote.common.TmdbApi
import io.reactivex.Observable
import io.reactivex.functions.BiFunction


class MovieRemoteDataSource(private val apiClient: TmdbApi): MovieDataSource {

    override fun getUpcommingMovies(page: Long): Observable<UpcomingMoviesResponse> {
       return Observable.zip(
               apiClient.genres(TmdbApi.API_KEY, TmdbApi.DEFAULT_LANGUAGE)
                       .flatMap {
                           Cache.cacheGenres(it.genres)
                           Observable.just(it)
                       },
               apiClient.upcomingMovies(TmdbApi.API_KEY, TmdbApi.DEFAULT_LANGUAGE, page, TmdbApi.DEFAULT_REGION),
                  BiFunction<GenreResponse, UpcomingMoviesResponse, UpcomingMoviesResponse> {
                      _: GenreResponse, movies: UpcomingMoviesResponse ->
                      movies
               })
    }

    override fun getAndSaveMovie(): Observable<Movie> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}