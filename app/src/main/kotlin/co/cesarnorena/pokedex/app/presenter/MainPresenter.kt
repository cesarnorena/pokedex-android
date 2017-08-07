package co.cesarnorena.pokedex.app.presenter

class MainPresenter(private val view: MainContract.View) : MainContract.Presenter {

    override fun onCreate() {
        view.showPokemonList()
    }
}