package com.arctouch.codechallenge.features.home

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.arctouch.codechallenge.R
import com.arctouch.codechallenge.base.BaseActivity
import com.arctouch.codechallenge.data.model.Movie
import kotlinx.android.synthetic.main.home_activity.*
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance

private const val FIRST_PAGE: Long = 1
private const val DEFAULT_INT = 0

class HomeActivity : BaseActivity<HomeView>(), HomeView {
    override val kodein by closestKodein()

    override val layout: Int = R.layout.home_activity

    override val presenter: HomePresenter by instance()

    private val homeAdapter: HomeAdapter = HomeAdapter(mutableListOf())

    private val linearLayoutManager: LinearLayoutManager = LinearLayoutManager(this)

    private var isLoading: Boolean = false

    private var isLastPage: Boolean = false

    private var currentPage: Long = FIRST_PAGE

    private var totalPages: Int = DEFAULT_INT

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

    override fun setPresenter() {
        presenter.attachView(this)
    }

    override fun initView() {

        recyclerView.apply {
            adapter = homeAdapter
            layoutManager = linearLayoutManager
            addOnScrollListener(paginationListener)
        }

    }

    //Call any code that have to run at activity onCreate
    override fun onCreate() {
    }

    override fun showMovies(movies: MutableList<Movie>, pages: Int) {
        totalPages = pages
        homeAdapter.movies = movies
        progressBar.visibility = View.GONE

        if(currentPage < totalPages) {
            homeAdapter.addProgressItem()
        } else {
            isLastPage = true
        }
    }

    override fun showNextPage(movies: MutableList<Movie>) {
        homeAdapter.removeProgressItem()
        isLoading = false

        homeAdapter.movies = movies

        if (currentPage < totalPages) {
            homeAdapter.addProgressItem()
        } else {
            isLastPage = true
        }
    }

    override fun showError(messageId: Int) {
    }

    override fun showError(messsage: String) {
    }

}
