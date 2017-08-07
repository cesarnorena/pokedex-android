package co.cesarnorena.pokedex.domain.interactors

import co.cesarnorena.pokedex.data.model.Pokedex
import co.cesarnorena.pokedex.domain.repository.RemoteRepository
import io.reactivex.Scheduler
import io.reactivex.Single

class GetPokedex(private val remoteRepository: RemoteRepository,
                 subscribeOn: Scheduler, observeOn: Scheduler)
    : SingleUseCase<GetPokedex.Input, GetPokedex.Output>(subscribeOn, observeOn) {

    override fun executeUseCase(values: Input?): Single<Output> {
        if (values == null) return Single.error { IllegalStateException() }
        return remoteRepository.getPokedex(values.id)
                .map { Output(it) }
    }

    data class Input(val id: Int) : SingleUseCase.Input

    data class Output(val pokedex: Pokedex) : SingleUseCase.Output
}