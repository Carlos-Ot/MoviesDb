package com.arctouch.codechallenge.data.source.remote

import com.arctouch.codechallenge.data.source.remote.api.TmdbApi
import okhttp3.OkHttpClient
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory


class ServiceClient {

     private val retrofit: Retrofit = Retrofit.Builder()
             .baseUrl(TmdbApi.URL)
             .addConverterFactory(MoshiConverterFactory.create())
             .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
             .client(OkHttpClient())
             .build()

    fun getApiClient(): TmdbApi = retrofit.create(TmdbApi::class.java)

}

/*
api.genres(TmdbApi.API_KEY, TmdbApi.DEFAULT_LANGUAGE)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Cache.cacheGenres(it.genres)
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            }
 */

val networkModule = Kodein.Module {
    bind<TmdbApi>() with singleton { ServiceClient().getApiClient() }
}