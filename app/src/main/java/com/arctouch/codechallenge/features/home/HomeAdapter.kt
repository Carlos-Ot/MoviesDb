package com.arctouch.codechallenge.features.home

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arctouch.codechallenge.R
import com.arctouch.codechallenge.data.model.Movie
import com.arctouch.codechallenge.util.MovieImageUrlBuilder
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.movie_item.view.*

private const val LOADING = 1
private const val MOVIE = 2

class HomeAdapter(items: List<Movie>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var isLoading: Boolean = false

    var movies: List<Movie> = items
    set(items) {
        field = items
        notifyDataSetChanged()
    }

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val movieImageUrlBuilder = MovieImageUrlBuilder()

        fun bind(movie: Movie) {
            itemView.titleTextView.text = movie.title
            itemView.genresTextView.text = movie.genres?.joinToString(separator = ", ") { it.name }
            itemView.releaseDateTextView.text = movie.releaseDate

            Glide.with(itemView)
                .load(movie.posterPath?.let { movieImageUrlBuilder.buildPosterUrl(it) })
                .apply(RequestOptions().placeholder(R.drawable.ic_image_placeholder))
                .into(itemView.posterImageView)
        }
    }

    class LoadingViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind() {
            //Call progress indicator
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View
        val viewItem: RecyclerView.ViewHolder

        if (viewType == LOADING) {
            view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
            viewItem = LoadingViewHolder(view)
        } else {
            view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
            viewItem = MovieViewHolder(view)
        }

        return viewItem
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        when (holder?.itemViewType) {
            LOADING -> (holder as? LoadingViewHolder)?.bind()

            MOVIE -> (holder as? MovieViewHolder)?.bind(movies[position])

            else -> (holder as? LoadingViewHolder)?.bind()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == (movies.size - 1) && isLoading) LOADING else MOVIE
    }

    override fun getItemCount() = movies.size

}
