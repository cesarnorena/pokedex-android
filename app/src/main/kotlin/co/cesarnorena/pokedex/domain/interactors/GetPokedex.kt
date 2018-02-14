package co.cesarnorena.pokedex.domain.interactors

import co.cesarnorena.pokedex.data.model.Pokedex
import co.cesarnorena.pokedex.domain.repository.LocalRepository
import co.cesarnorena.pokedex.domain.repository.RemoteRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers

class GetPokedex(private val remoteRepository: RemoteRepository,
                 private val localRepository: LocalRepository)
    : SingleUseCase<GetPokedex.Input, GetPokedex.Output>() {

    override fun executeUseCase(values: Input?): Single<Output> {
        if (values == null) return Single.error { IllegalStateException() }

        return remoteRepository.getPokedex(values.id)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess {
                    localRepository.savePokedex(it.pokedexEntries)
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeOn(AndroidSchedulers.mainThread())
                            .subscribe()
                }
                .map(::Output)
    }

    data class Input(val id: Int) : SingleUseCase.Input

    data class Output(val pokedex: Pokedex) : SingleUseCase.Output

}
