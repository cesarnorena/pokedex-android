package co.cesarnorena.pokedex.app.presenter

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import co.cesarnorena.pokedex.R
import co.cesarnorena.pokedex.app.presenter.pokemonList.PokemonListFragment
import co.cesarnorena.pokedex.app.util.extension.addFragment

class MainActivity : AppCompatActivity(), MainContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun showPokemonList() {
        val pokemonListFragment = PokemonListFragment()
        addFragment(pokemonListFragment, R.id.fragment_main_container)
    }

}