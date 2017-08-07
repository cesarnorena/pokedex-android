package co.cesarnorena.pokedex.domain.repository

import co.cesarnorena.pokedex.data.model.Pokedex
import co.cesarnorena.pokedex.data.model.Pokemon
import io.reactivex.Single

interface LocalRepository {

    fun getPokedex(): Single<Pokedex>
    fun getPokemon(): Single<Pokemon>
}