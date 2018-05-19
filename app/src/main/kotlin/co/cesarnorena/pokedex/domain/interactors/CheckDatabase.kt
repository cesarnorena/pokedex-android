package co.cesarnorena.pokedex.domain.interactors

import co.cesarnorena.pokedex.domain.repository.LocalRepository
import io.reactivex.Completable

class CheckDatabase(private val localRepository: LocalRepository) {

    fun execute(): Completable {
        return localRepository.getPokemon(1).toCompletable()
    }

}
