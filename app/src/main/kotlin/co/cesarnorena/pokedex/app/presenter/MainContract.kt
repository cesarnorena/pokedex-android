package co.cesarnorena.pokedex.app.presenter

interface MainContract {

    interface View {
        fun showPokemonList()
        fun showPokemonDetail()
    }

    interface Presenter {
        fun onCreate()
    }
}