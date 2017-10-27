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
import butterknife.BindView
import butterknife.ButterKnife
import co.cesarnorena.pokedex.R
import co.cesarnorena.pokedex.app.presenter.MainActivity
import co.cesarnorena.pokedex.data.model.PokedexEntry
import co.cesarnorena.pokedex.data.remote.PokemonService
import co.cesarnorena.pokedex.data.remote.client.ServiceFactory
import co.cesarnorena.pokedex.data.repository.PokemonRepository
import co.cesarnorena.pokedex.domain.interactors.GetPokedex

class PokemonListFragment : Fragment(), PokemonListContract.View {

    @BindView(R.id.pokemon_list_toolbar)
    lateinit var toolbar: Toolbar

    @BindView(R.id.pokemon_list_recyclerview)
    lateinit var recyclerView: RecyclerView

    lateinit var presenter: PokemonListContract.Presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_pokemon_list, container, false)
        ButterKnife.bind(this, view)
        setupToolbar()
        setupInjection()
        presenter.onCreate()
        return view
    }

    private fun setupToolbar() {
        (activity as MainActivity).setSupportActionBar(toolbar)
    }

    private fun setupInjection() {
        val remote = PokemonRepository(ServiceFactory.create(PokemonService::class.java,
                PokemonService.BASE_URL))
        val getPokedex = GetPokedex(remote)
        presenter = PokemonListPresenter(this, getPokedex)
    }

    override fun setupList(pokemonList: List<PokedexEntry>) {
        val adapter = PokemonListAdapter(context, pokemonList)
        adapter.onItemClick {
            presenter.onPokemonItemClick(it.number)
        }
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
    }

    override fun navigatePokemonDetail(pokemonId: Int) {
        (activity as MainActivity).showPokemonDetail(pokemonId)
    }

    override fun getContext(): Context = activity

}
