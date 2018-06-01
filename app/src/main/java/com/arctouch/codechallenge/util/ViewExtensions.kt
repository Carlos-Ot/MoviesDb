package com.arctouch.codechallenge.util

import android.support.v7.widget.RecyclerView
import android.view.View


fun <T : RecyclerView.ViewHolder> T.onClick(event: (view: View, position: Int, type: Int) -> Unit): T {
    itemView.setOnClickListener {
        event.invoke(it, adapterPosition, itemViewType)
    }
    return this
}