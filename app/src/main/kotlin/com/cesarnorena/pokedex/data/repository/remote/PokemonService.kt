package com.cesarnorena.pokedex.data.repository.remote

import com.cesarnorena.pokedex.data.model.Pokedex
import com.cesarnorena.pokedex.data.model.Pokemon
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonService {

    companion object {
        private const val IMAGE_URL = "http://assets.pokemon.com/assets/cms2/img/pokedex"
        fun getLargeImageUrl(pokemonId: String) = "$IMAGE_URL/full/$pokemonId.png"
        fun getShortImageUrl(pokemonId: String) = "$IMAGE_URL/detail/$pokemonId.png"
    }

    @GET("pokedex/{id}")
    fun getPokedex(@Path("id") id: Int): Single<Response<Pokedex>>

    @GET("pokemon/{id}")
    fun getPokemon(@Path("id") id: Int): Single<Response<Pokemon>>
}
