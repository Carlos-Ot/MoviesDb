package com.arctouch.codechallenge.features.home

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.LinearLayout
import com.arctouch.codechallenge.R
import com.arctouch.codechallenge.base.BaseActivity
import com.arctouch.codechallenge.data.model.Movie
import kotlinx.android.synthetic.main.home_activity.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance

class HomeActivity : BaseActivity<HomeView>(), HomeView {
    override val kodein by closestKodein()

    override val layout: Int = R.layout.home_activity

    override val presenter: HomePresenter by instance()

    private lateinit var homeAdapter: HomeAdapter

    override fun setPresenter() {
        presenter.attachView(this)
    }

    override fun initView() {
        homeAdapter = HomeAdapter(emptyList())

        recyclerView.adapter = homeAdapter
    }

    override fun start() {
        presenter.init()
    }

    override fun showMovies(movies: List<Movie>) {
        homeAdapter.movies = movies
        progressBar.visibility = View.GONE
    }

    override fun showError(messageId: Int) {
    }

    override fun showError(messsage: String) {
    }

}
