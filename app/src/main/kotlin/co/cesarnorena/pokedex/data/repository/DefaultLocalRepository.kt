package co.cesarnorena.pokedex.data.repository

import android.content.Context
import co.cesarnorena.pokedex.data.model.PokedexEntry
import co.cesarnorena.pokedex.data.model.Pokemon
import co.cesarnorena.pokedex.data.model.Specie
import co.cesarnorena.pokedex.data.repository.local.PokedexDatabase
import co.cesarnorena.pokedex.data.repository.local.PokemonEntity
import co.cesarnorena.pokedex.domain.repository.LocalRepository
import io.reactivex.Single
import javax.inject.Inject

class DefaultLocalRepository @Inject constructor(context: Context) : LocalRepository {

    private val pokemonDao = PokedexDatabase.getPokemonDao(context)

    override fun savePokedex(list: List<PokedexEntry>): Single<Unit> {
        return Single.create { emitter ->
            val pokemonList = list.map {
                PokemonEntity(it.number, it.specie.name)
            }
            pokemonDao.saveAll(pokemonList)
            emitter.onSuccess(Unit)
        }
    }

    override fun getPokedex(): Single<List<PokedexEntry>> {
        return pokemonDao.getAll().map { list ->
            list.map {
                PokedexEntry(it.id, Specie(it.name))
            }
        }
    }

    override fun savePokemon(pokemon: Pokemon) {
        pokemonDao.save(PokemonEntity(pokemon.id, pokemon.name))
    }

    override fun getPokemon(id: Int): Single<Pokemon> {
        return pokemonDao.findById(id).map {
            Pokemon(it.id, it.name, emptyList())
        }
    }
}
