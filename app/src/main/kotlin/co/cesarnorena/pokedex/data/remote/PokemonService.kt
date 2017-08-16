package co.cesarnorena.pokedex.data.remote

import co.cesarnorena.pokedex.data.model.Pokedex
import co.cesarnorena.pokedex.data.model.Pokemon
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonService {

    companion object {
        const val BASE_URL = "http://pokeapi.co/api/v2/"

        fun getLargeImageUrl(pokemonId: String) = "http://assets.pokemon.com/" +
                "assets/cms2/img/pokedex/full/$pokemonId.png"

        fun getShortImageUrl(pokemonId: String) = "http://assets.pokemon.com/" +
                "assets/cms2/img/pokedex/detail/$pokemonId.png"
    }

    @GET("pokedex/{id}")
    fun getPokedex(@Path("id") id: Int): Single<Response<Pokedex>>

    @GET("pokemon-species/{id}")
    fun getPokemon(@Path("id") id: Int): Single<Response<Pokemon>>
}