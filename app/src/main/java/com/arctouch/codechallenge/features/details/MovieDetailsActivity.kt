package com.arctouch.codechallenge.features.details

import android.util.Log
import android.view.View
import com.arctouch.codechallenge.R
import com.arctouch.codechallenge.base.BaseActivity
import com.arctouch.codechallenge.data.model.Movie
import com.arctouch.codechallenge.util.GlideApp
import com.arctouch.codechallenge.util.MovieImageUrlBuilder
import com.arctouch.codechallenge.util.formatLocalLongDate
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_movie_details.*
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance

private const val GENRES_SEPARATOR = ", "

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

    override fun onConnectionChanged(isConnected: Boolean) {
        Log.d("carlosottoboni", "IsConnected: $isConnected")
    }

    override fun showError(messageId: Int) {
        errorTextView.setText(messageId)
        hideContent()
    }

    override fun showError(messsage: String) {
        errorTextView.text = messsage
        hideContent()
    }

    private fun hideContent() {
        backdropImage.visibility = View.GONE
        posterImage.visibility = View.GONE
    }

    override fun showMovieDetail(movie: Movie) {
        val movieImageUrlBuilder = MovieImageUrlBuilder()

        GlideApp.with(this)
                .load(movie.backdropPath?.let { movieImageUrlBuilder.buildBackdropUrl(it) })
                .into(backdropImage)

        movieTitle.text = movie.title

        Glide.with(this)
                .load(movie.posterPath?.let { movieImageUrlBuilder.buildPosterUrl(it) })
                .into(posterImage)

        detailsOverview.text = movie.overview

        genresList.text = movie.genres?.joinToString(GENRES_SEPARATOR) { it.name }

        releaseDate.text = movie.releaseDate?.formatLocalLongDate(this)
    }
}
