package com.arctouch.codechallenge.features.details

import com.arctouch.codechallenge.R
import com.arctouch.codechallenge.base.BaseActivity
import com.arctouch.codechallenge.data.model.Movie
import kotlinx.android.synthetic.main.activity_movie_details.*
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance

class MovieDetailsActivity : BaseActivity<MovieDetailsView>(), MovieDetailsView {
    override val kodein by closestKodein()

    override val layout: Int = R.layout.activity_movie_details

    override val presenter: MovieDetailsPresenter by instance()

    private val args by lazy {
        MovieDetailsActivityArgs.getArguments(intent)
    }

    /**
     * @see BaseActivity.initView
     */
    override fun initView() {

    }

    /**
     * @see BaseActivity.setPresenter
     */
    override fun setPresenter() {
        presenter.attachView(this)
    }

    /**
     * @see BaseActivity.onCreate
     */
    override fun onCreate() {
        presenter.loadMovieDetails(args.movieId)
    }

    override fun showError(messageId: Int) {
    }

    override fun showError(messsage: String) {
    }

    override fun showMovieDetail(movie: Movie) {
        movieId.setText("Movie ID: ${movie.id} \n Title: ${movie.title}")
    }
}
