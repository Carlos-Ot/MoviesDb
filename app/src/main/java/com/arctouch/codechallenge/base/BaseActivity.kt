package com.arctouch.codechallenge.base

import android.content.BroadcastReceiver
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.arctouch.codechallenge.util.framework.ConnectionBroadcastReceiver
import org.kodein.di.KodeinAware

abstract class BaseActivity<V: BaseView> : AppCompatActivity(), KodeinAware, BaseView, ConnectionBroadcastReceiver.ConnectionListener {

    protected abstract val layout: Int
    protected abstract val presenter: BasePresenter<V>

    protected val broadcastReceiver = ConnectionBroadcastReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout)

        registerReceiver(broadcastReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))

        setPresenter()
        initView()
        onCreate()

    }

    /**
     * Initialize views for Activity
     */
    protected abstract fun initView()

    /**
     * Attach Activity to Presenter
     */
    protected abstract fun setPresenter()

    /**
     * Call any code that have to run at Activity.onCreate()
     */
    protected abstract fun onCreate()

    override fun onPause() {
        super.onPause()
        presenter.pause()
    }

    override fun onStop() {
        super.onStop()
        presenter.stop()
    }

    override fun onResume() {
        super.onResume()
        presenter.resume()

        ConnectionBroadcastReceiver.connectionListener = this
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.destroy()
        unregisterReceiver(broadcastReceiver)
    }

}
