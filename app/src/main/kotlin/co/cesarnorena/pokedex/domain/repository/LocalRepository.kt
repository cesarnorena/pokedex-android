package co.cesarnorena.pokedex.domain.repository

import co.cesarnorena.pokedex.data.model.PokedexEntry
import co.cesarnorena.pokedex.data.model.Pokemon
import io.reactivex.Completable
import io.reactivex.Single

interface LocalRepository {

    fun savePokedex(list: List<PokedexEntry>): Single<Unit>
    fun getPokedex(): Single<List<PokedexEntry>>
    fun savePokemon(pokemon: Pokemon)
    fun getPokemon(id: Int): Single<Pokemon>

}
