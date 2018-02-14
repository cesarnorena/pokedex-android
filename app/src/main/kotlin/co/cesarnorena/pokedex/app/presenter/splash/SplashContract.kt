package co.cesarnorena.pokedex.app.presenter.splash

interface SplashContract {

    interface View {
        fun showPokemonList()
    }

    interface Presenter {
        fun onCreateView()
        fun onDestroyView()
    }

}