package co.cesarnorena.pokedex.app.presenter.home.list

import co.cesarnorena.pokedex.app.libraries.reactivex.addDisposeBag
import co.cesarnorena.pokedex.domain.usecases.FetchPokedexEntries
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class PokemonListPresenter @Inject constructor(
    private val fetchPokedexEntries: FetchPokedexEntries
) {
    private var view: PokemonListView? = null

    private val disposeBag = CompositeDisposable()

    fun onCreateView() {
        getPokedex()
    }

    fun onDestroyView() {
        disposeBag.dispose()
    }

    private fun getPokedex() {
        fetchPokedexEntries().subscribe({ pokedexEntries ->
            view?.setupList(pokedexEntries)
        }, {
            view?.showNoInternetMessage(true)
        }).addDisposeBag(disposeBag)
    }

    fun onPokemonItemClick(pokemonId: Int) {
        view?.navigatePokemonDetail(pokemonId)
    }

    fun setView(view: PokemonListView?) {
        this.view = view
    }
}
