package co.cesarnorena.pokedex.domain.interactors

import io.reactivex.Completable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

abstract class CompletableUseCase<in Q : CompletableUseCase.Input>(
        private val subscribeOn: Scheduler = Schedulers.io(),
        private val observeOn: Scheduler = AndroidSchedulers.mainThread()) {

    fun execute(requestValues: Q? = null): Completable =
            executeUseCase(requestValues).subscribeOn(subscribeOn).observeOn(observeOn)

    protected abstract fun executeUseCase(values: Q?): Completable

    interface Input

}