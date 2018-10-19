package co.cesarnorena.pokedex.data.model

import co.cesarnorena.pokedex.app.extensions.formattedId
import co.cesarnorena.pokedex.data.repository.remote.PokemonService
import com.google.gson.annotations.SerializedName

data class Pokemon(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("types") val types: List<PokemonType>
) {
    val imageUrl: String get() = PokemonService.getLargeImageUrl(id.formattedId())
}

data class PokemonType(
    @SerializedName("slot") val position: Int,
    @SerializedName("type") val type: Type
)

data class Type(
    @SerializedName("name") val name: String
)
