package co.cesarnorena.pokedex.app.presenter.home.list

import co.cesarnorena.pokedex.domain.usecases.FetchPokedexEntries
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class PokemonListPresenter @Inject constructor(
    private val fetchPokedexEntries: FetchPokedexEntries
) {

    private var view: PokemonListView? = null
    private val disposable = CompositeDisposable()

    fun onCreateView() {
        getPokedex()
    }

    fun onDestroyView() {
        disposable.dispose()
    }

    private fun getPokedex() {
        fetchPokedexEntries().subscribe({ pokedexEntries ->
            view?.setupList(pokedexEntries)
        }, {
            view?.showNoInternetMessage(true)
        }).also {
            disposable.add(it)
        }
    }

    fun onPokemonItemClick(pokemonId: Int) {
        view?.navigatePokemonDetail(pokemonId)
    }

    fun setView(view: PokemonListView?) {
        this.view = view
    }
}
