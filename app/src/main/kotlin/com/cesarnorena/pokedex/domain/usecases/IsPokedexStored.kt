package com.cesarnorena.pokedex.domain.usecases

import com.cesarnorena.pokedex.domain.repository.LocalRepository
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class IsPokedexStored @Inject constructor(
    private val localRepository: LocalRepository
) {
    operator fun invoke(): Completable {
        return localRepository.getPokemon(1)
            .flatMapCompletable {
                Completable.complete()
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}
