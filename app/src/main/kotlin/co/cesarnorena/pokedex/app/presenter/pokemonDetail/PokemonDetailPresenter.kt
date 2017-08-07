package co.cesarnorena.pokedex.app.presenter.pokemonDetail

import co.cesarnorena.pokedex.domain.interactors.GetPokemon

class PokemonDetailPresenter(private val view: PokemonDetailContract.View,
                             private val getPokemon: GetPokemon) : PokemonDetailContract.Presenter {

    override fun onCreateView(pokemonId: Int) {
        getPokemon(pokemonId)
    }

    fun getPokemon(id: Int) {
        val request = GetPokemon.Input(id)
        getPokemon.execute(request).subscribe(
                { (pokemon) ->
                    view.updatePokemonData(pokemon)
                },
                { it.printStackTrace() })
    }
}