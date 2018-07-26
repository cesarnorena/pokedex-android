package co.cesarnorena.pokedex.domain.usecases

import co.cesarnorena.pokedex.data.model.PokedexEntry
import co.cesarnorena.pokedex.domain.repository.LocalRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GetPokedexEntries @Inject constructor(
        private val localRepository: LocalRepository
) {

    fun execute(): Single<List<PokedexEntry>> {
        return localRepository.getPokedex()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}
