package com.arctouch.codechallenge.data.source.remote.common

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

val networkModule = Kodein.Module {
    bind<TmdbApi>() with singleton { ServiceClient().getApiClient() }
}