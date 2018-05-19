package co.cesarnorena.pokedex.domain.interactors

import co.cesarnorena.pokedex.domain.repository.LocalRepository
import co.cesarnorena.pokedex.domain.repository.RemoteRepository
import io.reactivex.Single

class GetPokedex(private val remoteRepository: RemoteRepository,
                 private val localRepository: LocalRepository) {

    fun execute(id: Int): Single<Boolean> {
        return remoteRepository.getPokedex(id)
                .flatMap {
                    localRepository.savePokedex(it.pokedexEntries)
                            .map { true }
                }
    }

}
