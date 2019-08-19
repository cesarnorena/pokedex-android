package co.cesarnorena.pokedex.data.model

import co.cesarnorena.pokedex.app.libraries.extensions.formattedId
import co.cesarnorena.pokedex.data.repository.remote.PokemonService
import com.google.gson.annotations.SerializedName

data class Pokemon(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("types") val typeSlots: List<TypeSlot>
) {
    val imageUrl: String get() = PokemonService.getLargeImageUrl(id.formattedId())
}

data class TypeSlot(
    @SerializedName("slot") val position: Int,
    @SerializedName("type") val type: Type
)

data class Type(
    @SerializedName("name") val name: String
)
