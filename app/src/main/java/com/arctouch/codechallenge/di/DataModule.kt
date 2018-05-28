package com.arctouch.codechallenge.di

import com.arctouch.codechallenge.data.repositories.MovieRepository
import com.arctouch.codechallenge.data.source.dataSource.MovieDataSource
import com.arctouch.codechallenge.data.source.local.MovieLocalDataSource
import com.arctouch.codechallenge.data.source.remote.MovieRemoteDataSource
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton


val dataSourceModule = Kodein.Module {

    bind<MovieRepository>() with singleton { MovieRepository(instance(tag = "remote"), instance(tag = "local"))}

    bind<MovieDataSource>(tag = "remote") with provider { MovieRemoteDataSource(instance()) }

    bind<MovieDataSource>(tag = "local") with singleton { MovieLocalDataSource() }
}