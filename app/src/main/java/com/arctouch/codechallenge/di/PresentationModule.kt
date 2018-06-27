package com.arctouch.codechallenge.di

import com.arctouch.codechallenge.features.details.MovieDetailsInteractor
import com.arctouch.codechallenge.features.details.MovieDetailsPresenter
import com.arctouch.codechallenge.features.home.HomeInteractor
import com.arctouch.codechallenge.features.home.HomePresenter
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton


val presenterModule = Kodein.Module {

    bind<HomePresenter>() with provider { HomePresenter(instance()) }

    bind<MovieDetailsPresenter>() with provider { MovieDetailsPresenter(instance()) }

}

val interactorModule = Kodein.Module {

    bind<HomeInteractor>() with singleton { HomeInteractor(instance()) }

    bind<MovieDetailsInteractor>() with singleton { MovieDetailsInteractor(instance()) }
}