package co.cesarnorena.pokedex.data.local

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = PokemonEntity.NAME)
data class PokemonEntity(
        @PrimaryKey val id: Int,
        val name: String
){
    companion object {
        const val NAME: String = "pokemon"
    }
}
