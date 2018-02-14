package co.cesarnorena.pokedex.data.repository

import co.cesarnorena.pokedex.data.local.*
import co.cesarnorena.pokedex.data.model.PokedexEntry
import co.cesarnorena.pokedex.data.model.Pokemon
import co.cesarnorena.pokedex.data.model.Specie
import co.cesarnorena.pokedex.domain.repository.LocalRepository
import io.reactivex.Single
import io.realm.Realm

class RealmRepository : LocalRepository {

    private fun getRealm() = Realm.getDefaultInstance()

    override fun getPokedex(): Single<List<PokedexEntry>> {
        val realm = getRealm()

        return realm.findAll<PokedexDb>()
                .map {
                    it.map {
                        val specie = Specie(it.specie!!.name, it.specie!!.url)
                        PokedexEntry(it.number, specie)
                    }
                }
                .doFinally {
                    realm.close()
                }
    }

    override fun savePokedex(list: List<PokedexEntry>): Single<Unit> {
        val mappedList = list.map {
            val specie = SpecieDb(it.specie.name, it.specie.url)
            PokedexDb(it.number, specie)
        }
        val realm = getRealm()

        return realm.saveAll(mappedList)
                .doFinally(realm::close)
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
