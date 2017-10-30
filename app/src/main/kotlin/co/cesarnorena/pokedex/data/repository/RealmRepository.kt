package co.cesarnorena.pokedex.data.repository

import co.cesarnorena.pokedex.data.local.*
import co.cesarnorena.pokedex.data.model.PokedexEntry
import co.cesarnorena.pokedex.data.model.Pokemon
import co.cesarnorena.pokedex.domain.repository.LocalRepository
import io.reactivex.Single
import io.realm.Realm

class RealmRepository : LocalRepository {

    private fun getRealm() = Realm.getDefaultInstance()

    override fun getPokedex(): Single<List<PokedexEntry>> {
        val realm = getRealm()

        return realm.findAll<PokedexDb>()
                .map {
                    it.map { PokedexEntry(it.number, it.specie) }
                }
                .doFinally {
                    realm.close()
                }
    }

    override fun savePokedex(list: List<PokedexEntry>): Single<Unit> {
        val mappedList = list.map { PokedexDb(it.number, it.specie) }
        val realm = getRealm()

        return realm.saveAll(mappedList)
                .doFinally { realm.close() }
    }

    override fun getPokemon(id: Int): Single<Pokemon> {
        val realm = getRealm()

        return realm.findById<PokemonDb>(Pair(Pokemon.ID, id))
                .map {
                    Pokemon(it.id, it.name)
                }
    }

    override fun savePokemon(pokemon: Pokemon) {
        val realm = getRealm()
        return realm.save(PokemonDb(pokemon.id, pokemon.name))
    }

}
