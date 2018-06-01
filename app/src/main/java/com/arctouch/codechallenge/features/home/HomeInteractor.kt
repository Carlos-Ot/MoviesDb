package com.arctouch.codechallenge.features.home

import com.arctouch.codechallenge.data.Cache
import com.arctouch.codechallenge.data.model.Movie
import com.arctouch.codechallenge.data.model.UpcomingMoviesResponse
import com.arctouch.codechallenge.data.repositories.MovieRepository
import io.reactivex.Observable


class HomeInteractor(private val movieRepository: MovieRepository) {

    fun getUpcommingMovies(page: Long): Observable<Pair<MutableList<Movie>, Int>> {
        return movieRepository.getUpcommingMovies(page)
                .map { upComingMovies: UpcomingMoviesResponse ->
                    val moviesWithGenres = upComingMovies.results.map { movie ->
                        movie.copy(genres = Cache.genres.filter { movie.genreIds?.contains(it.id) == true })
                    }

                    val movies: MutableList<Movie> = mutableListOf()
                    movies.addAll(moviesWithGenres)
                    Pair(movies, upComingMovies.totalPages)
                }
    }
}