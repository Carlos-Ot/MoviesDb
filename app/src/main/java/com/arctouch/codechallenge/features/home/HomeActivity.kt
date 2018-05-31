package com.arctouch.codechallenge.features.home

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.arctouch.codechallenge.R
import com.arctouch.codechallenge.base.BaseActivity
import com.arctouch.codechallenge.data.model.Movie
import kotlinx.android.synthetic.main.home_activity.*
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance

class HomeActivity : BaseActivity<HomeView>(), HomeView {
    override val kodein by closestKodein()

    override val layout: Int = R.layout.home_activity

    override val presenter: HomePresenter by instance()

    private val homeAdapter: HomeAdapter = HomeAdapter(mutableListOf())

    private val linearLayoutManager: LinearLayoutManager = LinearLayoutManager(this)

    private var isLoading: Boolean = false

    private var isLastPage: Boolean = false

    var paginationListener: PaginationListener = object : PaginationListener(linearLayoutManager) {
        override fun loadPage() {
            isLoading = true
            presenter.loadMovies(2)
        }

        override fun totalPageCount(): Int {
            return 10
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

    override fun start() {
        presenter.init()
    }

    override fun showMovies(movies: MutableList<Movie>) {
        homeAdapter.movies = movies
        progressBar.visibility = View.GONE

        homeAdapter.addProgressItem()
    }

    override fun showNextPage(movies: MutableList<Movie>) {
        with(homeAdapter) {
            removeProgressItem()
            this.movies = movies
            addProgressItem()
        }
    }

    override fun showError(messageId: Int) {
    }

    override fun showError(messsage: String) {
    }

}
