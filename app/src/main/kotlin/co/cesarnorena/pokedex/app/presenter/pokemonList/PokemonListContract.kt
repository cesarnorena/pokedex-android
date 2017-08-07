package co.cesarnorena.pokedex.app.presenter.pokemonList

import co.cesarnorena.pokedex.data.model.PokedexEntry

interface PokemonListContract {

    interface View {
        fun setupList(pokemonList: List<PokedexEntry>)
    }

    interface Presenter {
        fun onCreateView()
    }
}