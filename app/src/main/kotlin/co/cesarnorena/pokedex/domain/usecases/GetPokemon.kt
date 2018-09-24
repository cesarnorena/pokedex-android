package co.cesarnorena.pokedex.domain.usecases

import co.cesarnorena.pokedex.data.model.Pokemon
import co.cesarnorena.pokedex.domain.repository.RemoteRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GetPokemon @Inject constructor(
    private val remoteRepository: RemoteRepository
) {

    fun execute(id: Int): Single<Pokemon> {
        return remoteRepository.getPokemon(id)
            .map { it }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}
