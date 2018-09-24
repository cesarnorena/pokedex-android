package co.cesarnorena.pokedex.data.model

import co.cesarnorena.pokedex.data.repository.remote.PokemonService
import com.google.gson.annotations.SerializedName

data class Pokemon(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String
) {

    val imageUrl: String get() = PokemonService.getLargeImageUrl(getFormattedId(id))

    companion object {
        fun getFormattedId(id: Int): String {
            return when {
                id < 10 -> "00$id"
                id < 100 -> "0$id"
                else -> id.toString()
            }
        }
    }
}
