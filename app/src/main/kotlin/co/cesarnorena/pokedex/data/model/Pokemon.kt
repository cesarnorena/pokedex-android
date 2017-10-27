package co.cesarnorena.pokedex.data.model

import co.cesarnorena.pokedex.data.remote.PokemonService
import com.google.gson.annotations.SerializedName

data class Pokemon(@SerializedName("id") val id: Int,
                   @SerializedName("name") val name: String) {

    val imageUrl: String get() = PokemonService.getLargeImageUrl(getFormattedId(id))

    companion object {
        const val ID = "id"

        fun getFormattedId(id: Int): String {
            val formattedId: String
            if (id < 10) {
                formattedId = "00$id"
            } else if (id < 100) {
                formattedId = "0$id"
            } else {
                formattedId = id.toString()
            }
            return formattedId
        }
    }

}
