package com.cesarnorena.pokedex.domain.repository

import com.cesarnorena.pokedex.data.model.PokedexEntry
import com.cesarnorena.pokedex.data.model.Pokemon
import io.reactivex.Completable
import io.reactivex.Single

interface LocalRepository {
    fun savePokedex(list: List<PokedexEntry>): Completable
    fun getPokedex(): Single<List<PokedexEntry>>
    fun getPokedexSize(): Int
    fun savePokemon(pokemon: Pokemon)
    fun getPokemon(id: Int): Single<Pokemon>
}
