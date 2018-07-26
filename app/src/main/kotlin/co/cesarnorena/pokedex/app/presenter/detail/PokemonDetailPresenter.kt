package co.cesarnorena.pokedex.app.presenter.detail

import co.cesarnorena.pokedex.domain.usecases.GetPokemon
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class PokemonDetailPresenter @Inject constructor(
        private val getPokemon: GetPokemon
) : PokemonDetailContract.Presenter {

    private var view: PokemonDetailContract.View? = null
    private var disposable: Disposable? = null

    override fun onCreateView(view: PokemonDetailContract.View, pokemonId: Int?) {
        this.view = view
        pokemonId?.let { getPokemon(it) } ?: throw IllegalArgumentException()
    }

    override fun onDestroyView() {
        disposable?.dispose()
    }

    private fun getPokemon(id: Int) {
        view?.showProgress(true)
        getPokemon.execute(id)
                .subscribe({ pokemon ->
                    view?.showProgress(false)
                    view?.updatePokemonData(pokemon)
                }, {
                    view?.showProgress(false)
                    it.printStackTrace()
                })
                .also {
                    disposable = it
                }
    }
}