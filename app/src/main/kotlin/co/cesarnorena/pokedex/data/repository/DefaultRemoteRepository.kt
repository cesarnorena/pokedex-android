package co.cesarnorena.pokedex.data.repository

import co.cesarnorena.pokedex.data.model.Pokedex
import co.cesarnorena.pokedex.data.model.Pokemon
import co.cesarnorena.pokedex.data.remote.PokemonService
import co.cesarnorena.pokedex.data.remote.client.HttpStatus
import co.cesarnorena.pokedex.domain.repository.RemoteRepository
import io.reactivex.Single

class DefaultRemoteRepository(private val pokemonService: PokemonService) : RemoteRepository {

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