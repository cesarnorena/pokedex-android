package co.cesarnorena.pokedex.domain.interactors

import co.cesarnorena.pokedex.domain.repository.LocalRepository
import io.reactivex.Completable

class CkeckDatabase(private val localRepository: LocalRepository)
    : CompletableUseCase<CompletableUseCase.Input>() {

    override fun executeUseCase(values: Input?): Completable {
        return localRepository.getPokemon(1).toCompletable()
    }

}
