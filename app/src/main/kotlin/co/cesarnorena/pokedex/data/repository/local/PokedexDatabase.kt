package co.cesarnorena.pokedex.data.repository.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

const val DATABASE_VERSION = 1
const val DATABASE_NAME = "pokemon_database"

@Database(
        entities = [PokemonEntity::class],
        version = DATABASE_VERSION,
        exportSchema = false
)
abstract class PokedexDatabase : RoomDatabase() {

    abstract fun pokemonDao(): PokemonDao

    companion object {
        fun getPokemonDao(context: Context): PokemonDao {
            return Room.databaseBuilder(context, PokedexDatabase::class.java, DATABASE_NAME)
                    .build().pokemonDao()
        }
    }
}
