package co.cesarnorena.pokedex.app.presenter.home.detail

import co.cesarnorena.pokedex.domain.usecases.GetPokemon
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class PokemonDetailPresenter @Inject constructor(
    private val getPokemon: GetPokemon
) {
    private var view: PokemonDetailView? = null

    private val disposable = CompositeDisposable()

    fun onCreateView(pokemonId: Int) {
        getPokemonDetails(pokemonId)
    }

    fun onDestroyView() {
        disposable.dispose()
    }

    private fun getPokemonDetails(id: Int) {
        view?.showProgress(true)

        getPokemon(id).subscribe({ pokemon ->
            view?.showProgress(false)
            view?.updatePokemonData(pokemon)
        }, {
            view?.showProgress(false)
            it.printStackTrace()
        }).also {
            disposable.add(it)
        }
    }

    fun setView(view: PokemonDetailView?) {
        this.view = view
    }
}
