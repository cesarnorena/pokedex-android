package co.cesarnorena.pokedex.app.presenter.home

interface HomeContract {

    interface View {
        fun showPokemonList()
        fun showPokemonDetail(id: Int)
    }

    interface Presenter {
        fun onCreateView(view: View)
    }
}
