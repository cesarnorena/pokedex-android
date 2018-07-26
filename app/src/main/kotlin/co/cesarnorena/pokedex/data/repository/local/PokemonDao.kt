package co.cesarnorena.pokedex.data.repository.local

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update
import io.reactivex.Single

@Dao
interface PokemonDao {

    @Insert(onConflict = REPLACE)
    fun save(pokemon: PokemonEntity)

    @Insert(onConflict = REPLACE)
    fun saveAll(pokemonList: List<PokemonEntity>)

    @Update(onConflict = REPLACE)
    fun update(pokemon: PokemonEntity)

    @Query("SELECT * FROM pokemon WHERE id = :id LIMIT 1")
    fun findById(id: Int): Single<PokemonEntity>

    @Query("SELECT * FROM pokemon")
    fun getAll(): Single<List<PokemonEntity>>
}
