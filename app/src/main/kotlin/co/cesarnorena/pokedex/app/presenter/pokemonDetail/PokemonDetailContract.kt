package co.cesarnorena.pokedex.app.presenter.pokemonDetail

import co.cesarnorena.pokedex.data.model.Pokemon

interface PokemonDetailContract {

    interface View {
        fun updatePokemonData(pokemon: Pokemon)
    }

    interface Presenter {
        fun onCreateView(pokemonId: Int)
    }
}