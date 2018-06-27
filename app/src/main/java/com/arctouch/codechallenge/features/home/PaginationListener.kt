package com.arctouch.codechallenge.features.home

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView


abstract class PaginationListener(private val layoutManager: LinearLayoutManager): RecyclerView.OnScrollListener() {

    override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val visibleCount = layoutManager.childCount
        val totalCount = layoutManager.itemCount
        val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

        if (!isLoading() && !isLastPage()) {
            if ((visibleCount + firstVisibleItemPosition) >= totalCount && firstVisibleItemPosition >= 0) {
                loadPage()
            }
        }

    }

    protected abstract fun loadPage()

    protected abstract fun totalPageCount(): Int

    protected abstract fun isLastPage(): Boolean

    protected abstract fun isLoading(): Boolean

}