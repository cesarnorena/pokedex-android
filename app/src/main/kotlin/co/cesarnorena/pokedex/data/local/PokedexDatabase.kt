package co.cesarnorena.pokedex.data.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(
        entities = [PokemonEntity::class],
        version = 1, exportSchema = false
)
abstract class PokedexDatabase : RoomDatabase() {

    abstract fun pokemonDao(): PokemonDao

    companion object {
        fun getPokemonDao(context: Context): PokemonDao {
            return Room.databaseBuilder(context, PokedexDatabase::class.java, PokemonEntity.NAME)
                    .build().pokemonDao()
        }
    }
}