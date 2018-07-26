package co.cesarnorena.pokedex.data.model

import com.google.gson.annotations.SerializedName

data class Pokedex(
        @SerializedName("pokemon_entries") val pokedexEntries: List<PokedexEntry>
)