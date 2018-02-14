package co.cesarnorena.pokedex.app.presenter.pokemonList

import co.cesarnorena.pokedex.domain.interactors.GetPokedex
import io.reactivex.disposables.Disposable

class PokemonListPresenter(private val view: PokemonListContract.View,
                           private val getPokedex: GetPokedex) : PokemonListContract.Presenter {

    private var mDisposable: Disposable? = null

    override fun onCreateView() {
        getPokedex()
    }

    override fun onDestroyView() {
        mDisposable?.dispose()
    }

    private fun getPokedex() {
        val request = GetPokedex.Input(1)
        mDisposable = getPokedex.execute(request).subscribe(
                { (pokedex) ->
                    view.setupList(pokedex.pokedexEntries)
                }, Throwable::printStackTrace)
    }

    override fun onPokemonItemClick(pokemonId: Int) {
        view.navigatePokemonDetail(pokemonId)
    }

}
