package com.arctouch.codechallenge.base

import android.support.annotation.StringRes


interface BaseView {
    fun showError(@StringRes messageId: Int)
    fun showError(messsage: String)
}