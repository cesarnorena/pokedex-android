package co.cesarnorena.pokedex.app.presenter.pokemonList

import android.app.Fragment
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.BindView
import butterknife.ButterKnife
import co.cesarnorena.pokedex.R
import co.cesarnorena.pokedex.app.presenter.MainActivity
import co.cesarnorena.pokedex.data.model.PokedexEntry
import co.cesarnorena.pokedex.data.remote.PokemonService
import co.cesarnorena.pokedex.data.repository.PokemonRepository
import co.cesarnorena.pokedex.domain.interactors.GetPokedex
import co.tappsi.taxidriver.data.remote.client.ServiceFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PokemonListFragment : Fragment(), PokemonListContract.View {

    @BindView(R.id.pokemon_list_listview)
    lateinit var recyclerView: RecyclerView

    lateinit var presenter: PokemonListContract.Presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_pokemon_list, container, false)
        ButterKnife.bind(this, view)
        setupInjection()
        presenter.onCreateView()
        return view
    }

    fun setupInjection() {
        val remote = PokemonRepository(ServiceFactory.create(PokemonService::class.java,
                PokemonService.BASE_URL))
        val subscribeOn = Schedulers.io()
        val observeOn = AndroidSchedulers.mainThread()
        val getPokedex = GetPokedex(remote, subscribeOn, observeOn)
        presenter = PokemonListPresenter(this, getPokedex)
    }

    override fun setupList(pokemonList: List<PokedexEntry>) {
        val adapter = PokemonListAdapter(context, pokemonList)
        adapter.onItemClick {
            presenter.onPokemonItemClick(it)
        }
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
    }

    override fun navigatePokemonDetail() {
        (activity as MainActivity).showPokemonDetail()
    }

    override fun getContext(): Context {
        return activity
    }
}