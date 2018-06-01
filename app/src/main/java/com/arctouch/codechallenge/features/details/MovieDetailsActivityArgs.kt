package com.arctouch.codechallenge.features.details

import android.content.Context
import android.content.Intent
import com.arctouch.codechallenge.base.BaseActivityArgs

private const val MOVIE_ID_KEY = "movie_id_key"

private const val DEFAULT_ID = -1L

class MovieDetailsActivityArgs(
        val movieId: Long
): BaseActivityArgs {

    override fun intent(activity: Context): Intent = Intent(
            activity, MovieDetailsActivity::class.java
    )
            .apply {
                putExtra(MOVIE_ID_KEY, movieId)
            }

    companion object {
        fun getArguments(intent: Intent): MovieDetailsActivityArgs {
            return MovieDetailsActivityArgs(movieId = intent.getLongExtra(MOVIE_ID_KEY, DEFAULT_ID))
        }
    }

}