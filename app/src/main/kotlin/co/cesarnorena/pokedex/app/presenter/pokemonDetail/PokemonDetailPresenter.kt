package co.cesarnorena.pokedex.app.presenter.pokemonDetail

import co.cesarnorena.pokedex.domain.interactors.GetPokemon
import io.reactivex.disposables.Disposable

class PokemonDetailPresenter(private val view: PokemonDetailContract.View,
                             private val getPokemon: GetPokemon) : PokemonDetailContract.Presenter {

    private var mDisposable: Disposable? = null

    override fun onCreate(pokemonId: Int) {
        getPokemon(pokemonId)
    }

    override fun onDestroy() {
        mDisposable?.dispose()
    }

    private fun getPokemon(id: Int) {
        view.showProgress(true)
        val request = GetPokemon.Input(id)
        mDisposable = getPokemon.execute(request).subscribe(
                { (pokemon) ->
                    view.showProgress(false)
                    view.updatePokemonData(pokemon)
                },
                {
                    view.showProgress(false)
                    it.printStackTrace()
                })
    }

}
