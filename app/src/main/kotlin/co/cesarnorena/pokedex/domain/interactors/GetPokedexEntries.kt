package co.cesarnorena.pokedex.domain.interactors

import co.cesarnorena.pokedex.data.model.PokedexEntry
import co.cesarnorena.pokedex.domain.repository.LocalRepository
import io.reactivex.Single

class GetPokedexEntries(private val localRepository: LocalRepository)
    : SingleUseCase<GetPokedexEntries.Input, GetPokedexEntries.Output>() {

    override fun executeUseCase(values: Input?): Single<Output> {
        return localRepository.getPokedex().map(::Output)
    }

    data class Input(val id: Int) : SingleUseCase.Input

    data class Output(val pokedexEntries: List<PokedexEntry>) : SingleUseCase.Output

}
