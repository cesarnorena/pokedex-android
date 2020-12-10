package com.cesarnorena.pokedex.domain.repository

import com.cesarnorena.pokedex.data.model.Pokedex
import com.cesarnorena.pokedex.data.model.Pokemon
import io.reactivex.Single

interface RemoteRepository {
    fun getPokedex(id: Int): Single<Pokedex>
    fun getPokemon(id: Int): Single<Pokemon>
}
