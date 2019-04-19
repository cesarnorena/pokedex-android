package co.cesarnorena.pokedex.domain.usecases

import co.cesarnorena.pokedex.domain.repository.LocalRepository
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GetPokedexSize @Inject constructor(
    private val localRepository: LocalRepository
) {
    operator fun invoke(): Single<Int> {
        return Single.fromCallable {
            localRepository.getPokedexSize()
        }.subscribeOn(Schedulers.io())
    }
}
