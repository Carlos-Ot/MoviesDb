package com.arctouch.codechallenge.base

import android.content.Context
import android.content.Intent


interface BaseActivityArgs {

    fun intent(activity: Context): Intent

    fun launch(activity: Context) = activity.startActivity(intent(activity))
}