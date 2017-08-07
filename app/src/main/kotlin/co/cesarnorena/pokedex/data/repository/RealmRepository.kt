package co.cesarnorena.pokedex.data.repository

import co.cesarnorena.pokedex.data.model.Pokedex
import co.cesarnorena.pokedex.data.model.Pokemon
import co.cesarnorena.pokedex.domain.repository.LocalRepository
import io.reactivex.Single

class RealmRepository : LocalRepository {

    override fun getPokedex(): Single<Pokedex> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getPokemon(): Single<Pokemon> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}