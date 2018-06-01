package com.arctouch.codechallenge.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.reactivex.disposables.CompositeDisposable
import org.kodein.di.KodeinAware

abstract class BaseActivity<V: BaseView> : AppCompatActivity(), KodeinAware, BaseView {

    protected abstract val layout: Int
    protected abstract val presenter: BasePresenter<V>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout)

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
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.destroy()
    }

}
