package co.cesarnorena.pokedex.app.presenter.list

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import co.cesarnorena.pokedex.R
import co.cesarnorena.pokedex.app.presenter.home.HomeActivity
import co.cesarnorena.pokedex.data.model.PokedexEntry
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class PokemonListFragment : DaggerFragment(), PokemonListContract.View {

    @BindView(R.id.pokemon_list_toolbar)
    lateinit var toolbar: Toolbar

    @BindView(R.id.pokemon_list_recyclerview)
    lateinit var recyclerView: RecyclerView

    @BindView(R.id.pokemon_list_no_internet)
    lateinit var noInternetText: TextView

    @Inject
    lateinit var presenter: PokemonListContract.Presenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_pokemon_list, container, false)
        ButterKnife.bind(this, view)
        setupToolbar()
        presenter.onCreateView(this)
        return view
    }

    override fun onDestroyView() {
        presenter.onDestroyView()
        super.onDestroyView()
    }

    private fun setupToolbar() {
        (activity as HomeActivity).setSupportActionBar(toolbar)
    }

    override fun setupList(pokemonList: List<PokedexEntry>) {
        val adapter = PokemonListAdapter(context!!, pokemonList)
        adapter.onItemClick {
            presenter.onPokemonItemClick(it.number)
        }
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
    }

    override fun showNoInternetMessage(show: Boolean) {
        noInternetText.visibility = if (show) View.VISIBLE else View.GONE
        recyclerView.visibility = if (show) View.GONE else View.VISIBLE
    }

    override fun navigatePokemonDetail(pokemonId: Int) {
        (activity as HomeActivity).showPokemonDetail(pokemonId)
    }

    override fun getContext(): Context? = activity
}
