package co.cesarnorena.pokedex.data.model

import com.google.gson.annotations.SerializedName

data class Pokedex(@SerializedName("id") val id: Int,
                   @SerializedName("name") val name: String,
                   @SerializedName("pokemon_entries") val pokedexEntries: List<PokedexEntry>)

data class PokedexEntry(@SerializedName("entry_number") val number: Int,
                        @SerializedName("pokemon_species") val specie: Specie) {

    private var imageUrl: String? = null

    fun getImageUrl(): String {
        if (imageUrl?.isEmpty() ?: true || imageUrl.equals("null", ignoreCase = true)) {
            imageUrl = "http://assets.pokemon.com/" +
                    "assets/cms2/img/pokedex/detail/${Pokemon.getFormattedId(number)}.png"
        }
        return imageUrl!!
    }
}

data class Specie(@SerializedName("name") val name: String,
                  @SerializedName("url") val url: String)
