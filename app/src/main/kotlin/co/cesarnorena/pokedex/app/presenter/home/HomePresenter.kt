package co.cesarnorena.pokedex.app.presenter.home

import javax.inject.Inject

class HomePresenter @Inject constructor() : HomeContract.Presenter {

    var view: HomeContract.View? = null

    override fun onCreateView(view: HomeContract.View) {
        this.view = view
        view.showPokemonList()
    }
}
