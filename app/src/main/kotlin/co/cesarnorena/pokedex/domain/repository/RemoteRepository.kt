package co.cesarnorena.pokedex.domain.repository

import co.cesarnorena.pokedex.data.model.Pokedex
import co.cesarnorena.pokedex.data.model.Pokemon
import io.reactivex.Single

interface RemoteRepository {
    fun getPokedex(id: Int): Single<Pokedex>
    fun getPokemon(id: Int): Single<Pokemon>
}
