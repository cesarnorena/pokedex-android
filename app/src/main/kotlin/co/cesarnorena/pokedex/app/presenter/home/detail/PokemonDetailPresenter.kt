package co.cesarnorena.pokedex.app.presenter.home.detail

import co.cesarnorena.pokedex.app.libraries.reactivex.addDisposeBag
import co.cesarnorena.pokedex.domain.usecases.GetPokedexSize
import co.cesarnorena.pokedex.domain.usecases.GetPokemon
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class PokemonDetailPresenter @Inject constructor(
    private val getPokemon: GetPokemon,
    private val getPokedexSize: GetPokedexSize
) {
    private var view: PokemonDetailView? = null

    private val disposeBag = CompositeDisposable()

    var pokemonId: Int = 0
    private var pokedexSize: Int = 0

    fun onCreateView() {
        getPokedexSize().subscribeBy {
            pokedexSize = it
        }.addDisposeBag(disposeBag)

        getPokemonDetails(pokemonId)
    }

    fun onDestroyView() {
        disposeBag.dispose()
    }

    fun onNextPokemon() {
        if (pokemonId < pokedexSize) getPokemonDetails(pokemonId + 1)
    }

    fun onPreviousPokemon() {
        if (pokemonId > 1) getPokemonDetails(pokemonId - 1)
    }

    private fun getPokemonDetails(id: Int) {
        getPokemon(id).doOnSubscribe {
            view?.showProgress(true)
        }.doFinally {
            view?.showProgress(false)
        }.subscribeBy { pokemon ->
            view?.updatePokemonData(pokemon)
            view?.updatePokemonTypes(pokemon.typeSlots)
            pokemonId = pokemon.id
        }.addDisposeBag(disposeBag)
    }

    fun setView(view: PokemonDetailView?) {
        this.view = view
    }
}
