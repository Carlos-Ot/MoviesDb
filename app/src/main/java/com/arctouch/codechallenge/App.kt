package com.arctouch.codechallenge

import android.app.Application
import com.arctouch.codechallenge.data.source.remote.common.networkModule
import com.arctouch.codechallenge.di.dataSourceModule
import com.arctouch.codechallenge.di.interactorModule
import com.arctouch.codechallenge.di.presenterModule
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.androidModule


class App: Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidModule(this@App))

        import(networkModule)
        import(presenterModule)
        import(interactorModule)
        import(dataSourceModule)
    }
}