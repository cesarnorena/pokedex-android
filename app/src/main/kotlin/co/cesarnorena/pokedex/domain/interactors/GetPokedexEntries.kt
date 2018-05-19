package co.cesarnorena.pokedex.domain.interactors

import co.cesarnorena.pokedex.data.model.PokedexEntry
import co.cesarnorena.pokedex.domain.repository.LocalRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GetPokedexEntries(private val localRepository: LocalRepository) {

    fun execute(): Single<List<PokedexEntry>> {
        return localRepository.getPokedex()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

}
