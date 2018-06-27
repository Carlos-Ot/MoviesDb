package com.arctouch.codechallenge.data.source.remote.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory


const val API_KEY_PARAM = "api_key"
const val LANGUAGE_PARAM = "language"

class ServiceClient {

     private val retrofit: Retrofit = Retrofit.Builder()
             .baseUrl(TmdbApi.URL)
             .addConverterFactory(MoshiConverterFactory.create())
             .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
             .client(buildOkHttpClient())
             .build()

    private fun buildOkHttpClient(): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()

        okHttpClient.addInterceptor { chain ->
            val request = chain.request()
            val url = request.url()

            val paramUrl = url.newBuilder()
                    .addQueryParameter(API_KEY_PARAM, TmdbApi.API_KEY)
                    .addQueryParameter(LANGUAGE_PARAM, TmdbApi.DEFAULT_LANGUAGE)
                    .build()

            val requestBuilder = request.newBuilder()
                    .url(paramUrl)

            val newRequest = requestBuilder.build()
            chain.proceed(newRequest)
        }

        return okHttpClient.build()
    }

    fun getApiClient(): TmdbApi = retrofit.create(TmdbApi::class.java)

}
