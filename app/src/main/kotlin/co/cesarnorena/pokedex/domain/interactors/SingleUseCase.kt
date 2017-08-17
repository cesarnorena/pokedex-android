package co.cesarnorena.pokedex.domain.interactors

import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

abstract class SingleUseCase<in Q : SingleUseCase.Input, P : SingleUseCase.Output>(
        private val subscribeOn: Scheduler = Schedulers.io(),
        private val observeOn: Scheduler = AndroidSchedulers.mainThread()) {

    fun execute(requestValues: Q? = null): Single<P> =
            executeUseCase(requestValues).subscribeOn(subscribeOn).observeOn(observeOn)

    protected abstract fun executeUseCase(values: Q?): Single<P>

    interface Input

    interface Output
}