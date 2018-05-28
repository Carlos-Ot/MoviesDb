package com.arctouch.codechallenge.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein

abstract class BaseActivity<V: BaseView> : AppCompatActivity(), KodeinAware, BaseView {

    protected abstract val layout: Int
    protected abstract val presenter: BasePresenter<V>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout)

        initView()
        setPresenter()
        start()

    }

    protected abstract fun initView()

    protected abstract fun setPresenter()

    protected abstract fun start()

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




//
//    protected val api: TmdbApi = Retrofit.Builder()
//        .baseUrl(TmdbApi.URL)
//        .client(OkHttpClient.Builder().build())
//        .addConverterFactory(MoshiConverterFactory.create())
//        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//        .build()
//        .create(TmdbApi::class.java)
