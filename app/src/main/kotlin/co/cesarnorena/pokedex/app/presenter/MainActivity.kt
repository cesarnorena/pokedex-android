package co.cesarnorena.pokedex.app.presenter

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import co.cesarnorena.pokedex.R
import co.cesarnorena.pokedex.app.presenter.pokemonDetail.PokemonDetailFragment
import co.cesarnorena.pokedex.app.presenter.pokemonList.PokemonListFragment
import co.cesarnorena.pokedex.app.utils.extensions.addFragment
import co.cesarnorena.pokedex.data.model.Pokemon

class MainActivity : AppCompatActivity(), MainContract.View {

    lateinit var presenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupInjection()
        presenter.onCreateView()
    }

    private fun setupInjection() {
        presenter = MainPresenter(this)
    }

    override fun showPokemonList() {
        addFragment(PokemonListFragment(), R.id.main_fragment_container)
    }

    override fun showPokemonDetail(id: Int) {
        val bundle = Bundle()
                .apply {
                    putInt(Pokemon.ID, id)
                }

        val fragment = PokemonDetailFragment()
                .apply {
                    arguments = bundle
                }

        addFragment(fragment, R.id.main_fragment_container, true)
    }

}
