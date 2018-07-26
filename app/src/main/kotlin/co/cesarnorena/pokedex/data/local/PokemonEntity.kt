package co.cesarnorena.pokedex.data.local

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "pokemon")
data class PokemonEntity(
        @PrimaryKey val id: Int,
        val name: String
)