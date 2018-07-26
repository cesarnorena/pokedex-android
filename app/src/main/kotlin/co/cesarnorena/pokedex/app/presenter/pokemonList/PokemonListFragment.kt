package co.cesarnorena.pokedex.app.presenter.pokemonList

import android.app.Fragment
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
import co.cesarnorena.pokedex.app.presenter.MainActivity
import co.cesarnorena.pokedex.data.model.PokedexEntry
import co.cesarnorena.pokedex.data.repository.DefaultLocalRepository
import co.cesarnorena.pokedex.domain.interactors.GetPokedexEntries

class PokemonListFragment : Fragment(), PokemonListContract.View {

    @BindView(R.id.pokemon_list_toolbar)
    lateinit var toolbar: Toolbar

    @BindView(R.id.pokemon_list_recyclerview)
    lateinit var recyclerView: RecyclerView

    @BindView(R.id.pokemon_list_no_internet)
    lateinit var noInternetText: TextView

    lateinit var presenter: PokemonListContract.Presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_pokemon_list, container, false)
        ButterKnife.bind(this, view)
        setupToolbar()
        setupInjection()
        presenter.onCreateView()
        return view
    }

    override fun onDestroyView() {
        presenter.onDestroyView()
        super.onDestroyView()
    }

    private fun setupToolbar() {
        (activity as MainActivity).setSupportActionBar(toolbar)
    }

    private fun setupInjection() {
        val localRepository = DefaultLocalRepository(context)
        val getLocalPokedex = GetPokedexEntries(localRepository)
        presenter = PokemonListPresenter(this, getLocalPokedex)
    }

    override fun setupList(pokemonList: List<PokedexEntry>) {
        val adapter = PokemonListAdapter(context, pokemonList)
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
        (activity as MainActivity).showPokemonDetail(pokemonId)
    }

    override fun getContext(): Context = activity
}