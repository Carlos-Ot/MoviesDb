package com.arctouch.codechallenge.features.home

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.Toast
import com.arctouch.codechallenge.R
import com.arctouch.codechallenge.base.BaseActivity
import com.arctouch.codechallenge.data.model.Movie
import com.arctouch.codechallenge.features.details.MovieDetailsActivityArgs
import kotlinx.android.synthetic.main.home_activity.*
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance

private const val FIRST_PAGE: Long = 1
private const val DEFAULT_INT = 0

class HomeActivity : BaseActivity<HomeView>(), HomeView {
    override val kodein by closestKodein()

    override val layout: Int = R.layout.home_activity

    override val presenter: HomePresenter by instance()

    private lateinit var homeAdapter: HomeAdapter

    private val linearLayoutManager: LinearLayoutManager = LinearLayoutManager(this)

    private var isLoading: Boolean = false

    private var isLastPage: Boolean = false

    private var currentPage: Long = FIRST_PAGE

    private var totalPages: Int = DEFAULT_INT

    private var isContentLoaded = false

    private val paginationListener: PaginationListener = object : PaginationListener(linearLayoutManager) {
        override fun loadPage() {
            isLoading = true
            currentPage++
            presenter.loadMovies(currentPage)
        }

        override fun totalPageCount(): Int {
            return totalPages
        }

        override fun isLastPage(): Boolean {
            return isLastPage
        }

        override fun isLoading(): Boolean {
            return isLoading
        }

    }
    private val itemClickListener: (View, Int, Int) -> Unit = {_, position, _ ->
        presenter.handleItemCLicked(homeAdapter.movies[position].id)
    }

    override fun setPresenter() {
        presenter.attachView(this)
    }

    override fun initView() {

        homeAdapter = HomeAdapter(mutableListOf(), itemClickListener)

        recyclerView.apply {
            adapter = homeAdapter
            layoutManager = linearLayoutManager
            addOnScrollListener(paginationListener)
        }

    }

    override fun onCreate() {
        presenter.loadMovies(isFirst = true)
    }

    override fun onConnectionChanged(isConnected: Boolean) {
        if (!isContentLoaded) {
            progressBar.visibility = View.VISIBLE
            errorTextView.visibility = View.GONE
            presenter.loadMovies(isFirst = true)
        }
    }

    override fun showMovies(movies: MutableList<Movie>, pages: Int) {
        totalPages = pages
        homeAdapter.movies = movies
        isContentLoaded = true
        progressBar.visibility = View.GONE
        errorTextView.visibility = View.GONE

        if(currentPage < totalPages) {
            homeAdapter.addProgressItem()
        } else {
            isLastPage = true
        }
    }

    override fun showNextPage(movies: MutableList<Movie>) {
        homeAdapter.removeProgressItem()
        isLoading = false
        isContentLoaded = true

        homeAdapter.movies = movies

        if (currentPage < totalPages) {
            homeAdapter.addProgressItem()
        } else {
            isLastPage = true
        }
    }

    override fun callMovieDetailsActivity(movieId: Long) {
        MovieDetailsActivityArgs(movieId).launch(this)
    }

    override fun showError(messageId: Int) {
        isContentLoaded = false
        errorTextView.setText(messageId)
        progressBar.visibility = View.GONE
    }

    override fun showError(messsage: String) {
        isContentLoaded = false
        errorTextView.text = messsage
        progressBar.visibility = View.GONE
    }

}
