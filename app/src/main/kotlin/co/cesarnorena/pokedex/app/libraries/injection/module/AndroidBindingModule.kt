package co.cesarnorena.pokedex.app.libraries.injection.module

import co.cesarnorena.pokedex.app.libraries.injection.ActivityScoped
import co.cesarnorena.pokedex.app.libraries.injection.FragmentScoped
import co.cesarnorena.pokedex.app.presenter.home.HomeActivity
import co.cesarnorena.pokedex.app.presenter.home.detail.PokemonDetailFragment
import co.cesarnorena.pokedex.app.presenter.home.list.PokemonListFragment
import co.cesarnorena.pokedex.app.presenter.splash.SplashActivity
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
