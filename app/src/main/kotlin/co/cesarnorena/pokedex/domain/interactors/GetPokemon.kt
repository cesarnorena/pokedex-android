package co.cesarnorena.pokedex.domain.interactors

import co.cesarnorena.pokedex.data.model.Pokemon
import co.cesarnorena.pokedex.domain.repository.RemoteRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GetPokemon(private val remoteRepository: RemoteRepository) {

    fun execute(id: Int): Single<Pokemon> {
        return remoteRepository.getPokemon(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map { it }
    }
}