package co.cesarnorena.pokedex.domain.interactors

import io.reactivex.Scheduler
import io.reactivex.Single

abstract class SingleUseCase<in Q : SingleUseCase.Input, P : SingleUseCase.Output>(
        val subscribeOn: Scheduler, val observeOn: Scheduler) {

    fun execute(requestValues: Q? = null): Single<P> {
        return executeUseCase(requestValues).subscribeOn(subscribeOn).observeOn(observeOn)
    }

    protected abstract fun executeUseCase(values: Q?): Single<P>

    interface Input

    interface Output
}