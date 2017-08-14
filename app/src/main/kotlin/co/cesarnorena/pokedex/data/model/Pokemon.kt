package co.cesarnorena.pokedex.data.model

import com.google.gson.annotations.SerializedName

data class Pokemon(@SerializedName("id") val id: Int,
                   @SerializedName("name") val name: String,
                   private var imageUrl: String?) {

    fun getImageUrl(): String {
        if (imageUrl == null) {
            imageUrl = "http://assets.pokemon.com/" +
                    "assets/cms2/img/pokedex/full/${getFormattedId(id)}.png"
        }
        return imageUrl!!
    }

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
