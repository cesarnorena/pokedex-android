package co.cesarnorena.pokedex.app.presenter.pokemonDetail

import co.cesarnorena.pokedex.data.model.Pokemon

interface PokemonDetailContract {

    interface View {
        fun updatePokemonData(pokemon: Pokemon)
        fun showProgress(show: Boolean)
    }

    interface Presenter {
        fun onCreate(pokemonId: Int)
        fun onDestroy()
    }

}
