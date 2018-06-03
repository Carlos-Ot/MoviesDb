package com.arctouch.codechallenge

import com.arctouch.codechallenge.data.model.Genre
import com.arctouch.codechallenge.data.model.Movie
import com.arctouch.codechallenge.data.repositories.MovieRepository
import com.arctouch.codechallenge.features.home.HomeInteractor
import com.arctouch.codechallenge.features.home.HomePresenter
import com.arctouch.codechallenge.features.home.HomeView
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit

/**
 * Created by caoj on 03/06/18.
 */
class HomePresenterTest {

    @Mock
    private lateinit var repository: MovieRepository

    @Mock
    private lateinit var interactor: HomeInteractor

    @Mock
    private lateinit var view: HomeView

    private lateinit var presenter: HomePresenter

    private val immediate = object : Scheduler() {
        override fun scheduleDirect(run: Runnable, delay: Long, unit: TimeUnit) = super.scheduleDirect(run, 0, unit)
        override fun createWorker() = ExecutorScheduler.ExecutorWorker(Executor { it.run() })
    }

    private val movies = mutableListOf(
            Movie(1,
                    "O estranho mundo de Jack",
                    "Sinopse do filme deveria vir aqui",
                    listOf(Genre(1, "animação"), Genre(2, "comédia")),
                    listOf(1, 2),
                    "/hu21jdsoafkodsdf",
                    "/gy2o3kodad532a2",
                    "1999-03-22"
            ),
            Movie(2,
                    "Jurassic Park",
                    "Sinopse do filme deveria vir aqui",
                    listOf(Genre(3, "ação"), Genre(4, "ficção")),
                    listOf(3, 4),
                    "/hu21jdsoafkodsdf",
                    "/gy2o3kodad532a2",
                    "1999-03-22"
            )
    )

    @Before
    fun setupHomePresenter() {
        MockitoAnnotations.initMocks(this)

        presenter = HomePresenter(interactor)

        presenter.attachView(view)

        RxAndroidPlugins.setInitMainThreadSchedulerHandler { immediate }
        RxJavaPlugins.setInitIoSchedulerHandler { immediate }
    }

    @Test
    fun loadFirstPageAndShow() {

        whenever(interactor.getUpcommingMovies(1)).thenReturn(Observable.just(Pair(movies, 2)))

        presenter.loadMovies(isFirst = true)

        verify(view).showMovies(movies, 2)

    }

    @Test
    fun loadNextPageAndShow() {
        whenever(interactor.getUpcommingMovies(2)).thenReturn(Observable.just(Pair(movies, 2)))

        presenter.loadMovies(2)

        verify(view).showNextPage(movies)
    }

    @Test
    fun loadFirstPageOnError() {

        whenever(interactor.getUpcommingMovies(1)).thenReturn(Observable.error(Exception()))

        presenter.loadMovies(isFirst = true)

        verify(view).showError(R.string.movieDetailsLoadingError)
    }

    @Test
    fun loadNextPageOnError() {

        whenever(interactor.getUpcommingMovies(2)).thenReturn(Observable.error(Exception()))

        presenter.loadMovies(2)

        verify(view).showError(R.string.movieDetailsLoadingError)
    }
}