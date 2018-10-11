package co.cesarnorena.pokedex.domain.usecases

import co.cesarnorena.pokedex.domain.repository.LocalRepository
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HasPokemonListStored @Inject constructor(
    private val localRepository: LocalRepository
) {
    operator fun invoke(): Completable {
        return localRepository.getPokemon(1)
            .toCompletable()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}
