package co.cesarnorena.pokedex.app.presenter.pokemonList

import co.cesarnorena.pokedex.domain.interactors.GetPokedexEntries
import io.reactivex.disposables.Disposable

class PokemonListPresenter(private val view: PokemonListContract.View,
                           private val getPokedexEntries: GetPokedexEntries)
    : PokemonListContract.Presenter {

    private var mDisposable: Disposable? = null

    override fun onCreateView() {
        getPokedex()
    }

    override fun onDestroyView() {
        mDisposable?.dispose()
    }

    private fun getPokedex() {
        mDisposable = getPokedexEntries.execute().subscribe(
                { (pokedexEntries) ->
                    view.setupList(pokedexEntries)
                },
                { error ->
                    view.showNoInternetMessage(true)
                    error.printStackTrace()
                })
    }

    override fun onPokemonItemClick(pokemonId: Int) {
        view.navigatePokemonDetail(pokemonId)
    }

}
