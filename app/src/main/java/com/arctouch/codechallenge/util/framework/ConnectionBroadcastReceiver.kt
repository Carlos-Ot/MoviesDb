package com.arctouch.codechallenge.util.framework

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance

/**
 * Created by caoj on 02/06/18.
 */
class ConnectionBroadcastReceiver(): BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        if (connectionListener != null) {
            connectionListener!!.onConnectionChanged(isConnected(context))
        }
    }

    private fun isConnected(context: Context): Boolean {
        val connectivityService: ConnectivityManager? =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val activeNetwork: NetworkInfo? = connectivityService?.activeNetworkInfo

        return activeNetwork!= null && activeNetwork.isConnectedOrConnecting
    }

    interface ConnectionListener {

        fun onConnectionChanged(isConnected: Boolean)
    }

    companion object {
        var connectionListener: ConnectionListener? = null
    }
}