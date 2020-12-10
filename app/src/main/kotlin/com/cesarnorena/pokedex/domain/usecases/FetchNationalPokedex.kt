package com.cesarnorena.pokedex.domain.usecases

import com.cesarnorena.pokedex.domain.repository.LocalRepository
import com.cesarnorena.pokedex.domain.repository.RemoteRepository
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FetchNationalPokedex @Inject constructor(
    private val remoteRepository: RemoteRepository,
    private val localRepository: LocalRepository
) {
    operator fun invoke(id: Int): Completable {
        return remoteRepository.getPokedex(id)
            .flatMapCompletable {
                localRepository.savePokedex(it.pokedexEntries)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}
