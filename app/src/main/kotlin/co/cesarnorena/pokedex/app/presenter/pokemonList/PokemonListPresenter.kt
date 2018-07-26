package co.cesarnorena.pokedex.app.presenter.pokemonList

import co.cesarnorena.pokedex.domain.interactors.GetPokedexEntries
import io.reactivex.disposables.CompositeDisposable

class PokemonListPresenter(private val view: PokemonListContract.View,
                           private val getPokedexEntries: GetPokedexEntries)
    : PokemonListContract.Presenter {

    private var disposable = CompositeDisposable()

    override fun onCreateView() {
        getPokedex()
    }

    override fun onDestroyView() {
        disposable.dispose()
    }

    private fun getPokedex() {
        getPokedexEntries.execute()
                .subscribe({ pokedexEntries ->
                    view.setupList(pokedexEntries)
                }, { error ->
                    view.showNoInternetMessage(true)
                    error.printStackTrace()
                }).also {
                    disposable.add(it)
                }
    }

    override fun onPokemonItemClick(pokemonId: Int) {
        view.navigatePokemonDetail(pokemonId)
    }
}