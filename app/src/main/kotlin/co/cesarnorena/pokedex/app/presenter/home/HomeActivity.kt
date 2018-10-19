package co.cesarnorena.pokedex.app.presenter.home

import android.os.Bundle
import co.cesarnorena.pokedex.R
import co.cesarnorena.pokedex.app.extensions.addFragment
import co.cesarnorena.pokedex.app.presenter.detail.PokemonDetailFragment
import co.cesarnorena.pokedex.app.presenter.list.PokemonListFragment
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class HomeActivity : DaggerAppCompatActivity(), HomeContract.View {

    @Inject
    lateinit var presenter: HomeContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        presenter.onCreateView(this)
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
