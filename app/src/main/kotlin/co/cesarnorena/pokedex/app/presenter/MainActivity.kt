package co.cesarnorena.pokedex.app.presenter

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import co.cesarnorena.pokedex.R
import co.cesarnorena.pokedex.app.presenter.pokemonDetail.PokemonDetailFragment
import co.cesarnorena.pokedex.app.presenter.pokemonList.PokemonListFragment
import co.cesarnorena.pokedex.app.utils.extensions.addFragment
import co.cesarnorena.pokedex.app.utils.extensions.replaceFragment
import co.cesarnorena.pokedex.data.model.Pokemon

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
        addFragment(PokemonListFragment(), R.id.fragment_main_container)
    }

    override fun showPokemonDetail(id: Int) {
        val args = Bundle()
        args.putInt(Pokemon.ID, id)
        val fragment = PokemonDetailFragment()
        fragment.arguments = args
        addFragment(fragment, R.id.fragment_main_container, true)
    }

}