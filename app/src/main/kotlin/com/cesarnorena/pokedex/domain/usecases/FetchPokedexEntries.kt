package com.cesarnorena.pokedex.domain.usecases

import com.cesarnorena.pokedex.data.model.PokedexEntry
import com.cesarnorena.pokedex.domain.repository.LocalRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FetchPokedexEntries @Inject constructor(
    private val localRepository: LocalRepository
) {
    operator fun invoke(): Single<List<PokedexEntry>> {
        return localRepository.getPokedex()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}
