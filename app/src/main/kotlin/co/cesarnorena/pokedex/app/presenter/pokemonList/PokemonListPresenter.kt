package co.cesarnorena.pokedex.app.presenter.pokemonList

import co.cesarnorena.pokedex.domain.interactors.GetPokedex

class PokemonListPresenter(private val view: PokemonListContract.View,
                           private val getPokedex: GetPokedex) : PokemonListContract.Presenter {

    override fun onCreateView() {
        getPokedex()
    }

    fun getPokedex() {
        val request = GetPokedex.Input(1)
        getPokedex.execute(request).subscribe(
                { (pokedex) ->
                    view.setupList(pokedex.pokedexEntries)
                },
                { it.printStackTrace() })
    }
}