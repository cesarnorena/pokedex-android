package co.cesarnorena.pokedex.app.presenter

interface MainContract {

    interface View {
        fun showPokemonList()
    }

    interface Presenter {
        fun onCreate()
    }
}