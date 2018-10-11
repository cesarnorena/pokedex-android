package co.cesarnorena.pokedex.app.presenter.detail

import co.cesarnorena.pokedex.domain.usecases.GetPokemon
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class PokemonDetailPresenter @Inject constructor(
    private val getPokemon: GetPokemon
) : PokemonDetailContract.Presenter {

    private var view: PokemonDetailContract.View? = null
    private val disposable = CompositeDisposable()

    override fun onCreateView(view: PokemonDetailContract.View, pokemonId: Int) {
        this.view = view
        getPokemonDetails(pokemonId)
    }

    override fun onDestroyView() {
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
}
