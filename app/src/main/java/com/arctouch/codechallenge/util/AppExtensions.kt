package com.arctouch.codechallenge.util

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.text.format.DateFormat
import android.view.View
import java.text.SimpleDateFormat
import java.util.*


fun <T : RecyclerView.ViewHolder> T.onClick(event: (view: View, position: Int, type: Int) -> Unit): T {
    itemView.setOnClickListener {
        event.invoke(it, adapterPosition, itemViewType)
    }
    return this
}

private const val API_DATE_FORMAT = "yyyy-MM-dd"
fun String.formatLocalLongDate(context: Context): String {
    var formattedDate = ""
    if (this.isNotEmpty()) {
        val formatter = SimpleDateFormat(API_DATE_FORMAT)
        val date = formatter.parse(this)

        val dateFormat = DateFormat.getLongDateFormat(context)

        formattedDate = dateFormat.format(date)
    }

    return formattedDate
}

fun String.formatLocalDate(context: Context): String {
    var formattedDate = ""
    if (this.isNotEmpty()) {
        val formatter = SimpleDateFormat(API_DATE_FORMAT)
        val date = formatter.parse(this)

        val dateFormat = DateFormat.getDateFormat(context)

        formattedDate = dateFormat.format(date)
    }

    return formattedDate
}