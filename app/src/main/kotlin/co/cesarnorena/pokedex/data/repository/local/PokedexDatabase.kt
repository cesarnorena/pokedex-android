package co.cesarnorena.pokedex.data.repository.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

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
