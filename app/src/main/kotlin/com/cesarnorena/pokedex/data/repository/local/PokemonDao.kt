package com.cesarnorena.pokedex.data.repository.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update
import io.reactivex.Single

@Dao
interface PokemonDao {

    @Insert(onConflict = REPLACE)
    fun save(pokemon: PokemonEntity)

    @Insert(onConflict = REPLACE)
    fun saveAll(pokemonList: List<PokemonEntity>)

    @Update(onConflict = REPLACE)
    fun update(pokemon: PokemonEntity)

    @Query("SELECT * FROM pokemon WHERE id = :id")
    fun findById(id: Int): Single<PokemonEntity>

    @Query("SELECT * FROM pokemon")
    fun getAll(): Single<List<PokemonEntity>>

    @Query("SELECT COUNT(*) FROM pokemon")
    fun getSize(): Int
}
