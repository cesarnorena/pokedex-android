package co.cesarnorena.pokedex.data.model

import com.google.gson.annotations.SerializedName

data class Pokedex(@SerializedName("id") val id: Int,
                   @SerializedName("name") val name: String,
                   @SerializedName("pokemon_entries") val pokedexEntries: List<PokedexEntry>)
