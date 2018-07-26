package co.cesarnorena.pokedex.app.presenter.pokemonDetail

import co.cesarnorena.pokedex.domain.interactors.GetPokemon
import io.reactivex.disposables.Disposable

class PokemonDetailPresenter(private val view: PokemonDetailContract.View,
                             private val getPokemon: GetPokemon) : PokemonDetailContract.Presenter {

    private var disposable: Disposable? = null

    override fun onCreateView(pokemonId: Int) {
        getPokemon(pokemonId)
    }

    override fun onDestroyView() {
        disposable?.dispose()
    }

    private fun getPokemon(id: Int) {
        view.showProgress(true)
        getPokemon.execute(id)
                .subscribe({ pokemon ->
                    view.showProgress(false)
                    view.updatePokemonData(pokemon)
                }, {
                    view.showProgress(false)
                    it.printStackTrace()
                })
                .also {
                    disposable = it
                }
    }
}