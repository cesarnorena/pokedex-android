package com.cesarnorena.pokedex.data.repository

import com.cesarnorena.pokedex.data.model.Pokedex
import com.cesarnorena.pokedex.data.model.Pokemon
import com.cesarnorena.pokedex.data.repository.remote.PokemonService
import com.cesarnorena.pokedex.data.repository.remote.client.HttpStatus
import com.cesarnorena.pokedex.domain.repository.RemoteRepository
import io.reactivex.Single

class DefaultRemoteRepository(
    private val pokemonService: PokemonService
) : RemoteRepository {

    override fun getPokedex(id: Int): Single<Pokedex> {
        return pokemonService.getPokedex(id).map { response ->
            when (response.code()) {
                HttpStatus.OK -> response.body()
                else -> throw Exception()
            }
        }
    }

    override fun getPokemon(id: Int): Single<Pokemon> {
        return pokemonService.getPokemon(id).map { response ->
            when (response.code()) {
                HttpStatus.OK -> response.body()
                else -> throw Exception()
            }
        }
    }
}
