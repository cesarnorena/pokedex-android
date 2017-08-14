package co.cesarnorena.pokedex.app.presenter

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import co.cesarnorena.pokedex.R
import co.cesarnorena.pokedex.app.presenter.pokemonDetail.PokemonDetailFragment
import co.cesarnorena.pokedex.app.presenter.pokemonList.PokemonListFragment
import co.cesarnorena.pokedex.app.utils.extensions.replaceFragment

class MainActivity : AppCompatActivity(), MainContract.View {

    lateinit var presenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupInjection()
        presenter.onCreate()
    }

    fun setupInjection() {
        presenter = MainPresenter(this)
    }

    override fun showPokemonList() {
        replaceFragment(PokemonListFragment(), R.id.fragment_main_container)
    }

    override fun showPokemonDetail() {
        replaceFragment(PokemonDetailFragment(), R.id.fragment_main_container)
    }

}