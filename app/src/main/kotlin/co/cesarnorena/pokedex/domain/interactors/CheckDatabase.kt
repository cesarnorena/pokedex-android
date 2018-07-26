package co.cesarnorena.pokedex.domain.interactors

import co.cesarnorena.pokedex.domain.repository.LocalRepository
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CheckDatabase(private val localRepository: LocalRepository) {

    fun execute(): Completable {
        return localRepository.getPokemon(1).toCompletable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}