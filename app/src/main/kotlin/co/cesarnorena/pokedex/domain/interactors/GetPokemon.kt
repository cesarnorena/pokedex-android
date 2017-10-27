package co.cesarnorena.pokedex.domain.interactors

import co.cesarnorena.pokedex.data.model.Pokemon
import co.cesarnorena.pokedex.domain.repository.RemoteRepository
import io.reactivex.Single

class GetPokemon(private val remoteRepository: RemoteRepository)
    : SingleUseCase<GetPokemon.Input, GetPokemon.Output>() {

    override fun executeUseCase(values: Input?): Single<Output> {
        if (values == null) return Single.error { IllegalStateException() }
        return remoteRepository.getPokemon(values.id)
                .map { Output(it) }
    }

    data class Input(val id: Int) : SingleUseCase.Input

    data class Output(val pokemon: Pokemon) : SingleUseCase.Output

}
