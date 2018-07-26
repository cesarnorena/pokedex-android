package co.cesarnorena.pokedex.app.presenter

interface MainContract {

    interface View {
        fun showPokemonList()
        fun showPokemonDetail(id: Int)
    }

    interface Presenter {
        fun onCreateView()
    }
}