package co.cesarnorena.pokedex.app.presenter.home

import android.os.Bundle
import co.cesarnorena.pokedex.R
import co.cesarnorena.pokedex.app.libraries.extensions.addFragment
import co.cesarnorena.pokedex.app.presenter.home.detail.PokemonDetailFragment
import co.cesarnorena.pokedex.app.presenter.home.list.PokemonListFragment
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

interface HomeView {
    fun showPokemonList()
    fun showPokemonDetail(id: Int)
    fun changePokemonDetail(id: Int)
}

class HomeActivity : DaggerAppCompatActivity(), HomeView {

    @Inject
    lateinit var presenter: HomePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        presenter.setView(this)
        presenter.onCreateView()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        presenter.setView(null)
        super.onSaveInstanceState(outState)
    }

    override fun showPokemonList() {
        addFragment(PokemonListFragment(), R.id.fragmentContainer)
    }

    override fun showPokemonDetail(id: Int) {
        val fragment = PokemonDetailFragment.newInstance(id)
        addFragment(fragment, R.id.fragmentContainer, true)
    }

    override fun changePokemonDetail(id: Int) {
        val fragment = PokemonDetailFragment.newInstance(id)
        addFragment(fragment, R.id.fragmentContainer, true)
    }
}
