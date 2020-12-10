package com.cesarnorena.pokedex.app.libraries.injection.module

import com.cesarnorena.pokedex.app.libraries.injection.ActivityScoped
import com.cesarnorena.pokedex.app.libraries.injection.FragmentScoped
import com.cesarnorena.pokedex.app.presenter.home.HomeActivity
import com.cesarnorena.pokedex.app.presenter.home.detail.PokemonDetailFragment
import com.cesarnorena.pokedex.app.presenter.home.list.PokemonListFragment
import com.cesarnorena.pokedex.app.presenter.splash.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector
    abstract fun splashActivity(): SplashActivity

    @ActivityScoped
    @ContributesAndroidInjector
    abstract fun homeActivity(): HomeActivity
}

@Module
abstract class FragmentBindingModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun PokemonDetailFragment(): PokemonDetailFragment

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun PokemonListFragment(): PokemonListFragment
}
