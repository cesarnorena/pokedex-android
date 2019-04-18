package co.cesarnorena.pokedex.app.presenter.home

import javax.inject.Inject

class HomePresenter @Inject constructor() {

    private var view: HomeView? = null

    fun onCreateView() {
        view?.showPokemonList()
    }

    fun setView(view: HomeView?) {
        this.view = view
    }
}
