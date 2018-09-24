package co.cesarnorena.pokedex.app.presenter.home.injection

import co.cesarnorena.pokedex.app.injection.ActivityScoped
import co.cesarnorena.pokedex.app.injection.FragmentScoped
import co.cesarnorena.pokedex.app.presenter.detail.PokemonDetailContract
import co.cesarnorena.pokedex.app.presenter.detail.PokemonDetailFragment
import co.cesarnorena.pokedex.app.presenter.detail.PokemonDetailPresenter
import co.cesarnorena.pokedex.app.presenter.home.HomeContract
import co.cesarnorena.pokedex.app.presenter.home.HomePresenter
import co.cesarnorena.pokedex.app.presenter.list.PokemonListContract
import co.cesarnorena.pokedex.app.presenter.list.PokemonListFragment
import co.cesarnorena.pokedex.app.presenter.list.PokemonListPresenter
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class HomeModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun pokemonListFragment(): PokemonListFragment

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun pokemonDetailFragment(): PokemonDetailFragment

    @ActivityScoped
    @Binds
    abstract fun bindHomePresenter(presenter: HomePresenter): HomeContract.Presenter

    @FragmentScoped
    @Binds
    abstract fun bindPokemonListPresenter(
        PokemonListPresenter: PokemonListPresenter
    ): PokemonListContract.Presenter

    @FragmentScoped
    @Binds
    abstract fun bindPokemonDetailPresenter(
        pokemonDetailPresenter: PokemonDetailPresenter
    ): PokemonDetailContract.Presenter
}
