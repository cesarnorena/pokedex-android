package co.cesarnorena.pokedex.app.presenter.list

import co.cesarnorena.pokedex.domain.usecases.GetPokedexEntries
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class PokemonListPresenter @Inject constructor(
        private val getPokedexEntries: GetPokedexEntries
) : PokemonListContract.Presenter {

    private var view: PokemonListContract.View? = null
    private var disposable = CompositeDisposable()

    override fun onCreateView(view: PokemonListContract.View) {
        this.view = view
        getPokedex()
    }

    override fun onDestroyView() {
        view = null
        disposable.dispose()
    }

    private fun getPokedex() {
        getPokedexEntries.execute()
                .subscribe({ pokedexEntries ->
                    view?.setupList(pokedexEntries)
                }, { error ->
                    view?.showNoInternetMessage(true)
                    error.printStackTrace()
                }).also {
                    disposable.add(it)
                }
    }

    override fun onPokemonItemClick(pokemonId: Int) {
        view?.navigatePokemonDetail(pokemonId)
    }
}