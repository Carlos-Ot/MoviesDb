package com.arctouch.codechallenge.features.details

import android.content.Context
import android.content.Intent
import com.arctouch.codechallenge.base.BaseActivityArgs

private const val MOVIE_ID_KEY = "movie_id_key"

private const val DEFAULT_ID = -1

class MovieDetailsActivityArgs(
        val movieId: Int
): BaseActivityArgs {

    override fun intent(activity: Context): Intent = Intent(
            activity, MovieDetailsActivity::class.java
    )
            .apply {
                putExtra(MOVIE_ID_KEY, movieId)
            }

    companion object {
        fun getArguments(intent: Intent): MovieDetailsActivityArgs {
            return MovieDetailsActivityArgs(movieId = intent.getIntExtra(MOVIE_ID_KEY, DEFAULT_ID))
        }
    }

}