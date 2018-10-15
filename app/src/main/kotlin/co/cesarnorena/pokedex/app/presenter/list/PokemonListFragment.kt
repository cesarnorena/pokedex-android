package co.cesarnorena.pokedex.app.presenter.list

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import co.cesarnorena.pokedex.R
import co.cesarnorena.pokedex.app.presenter.home.HomeActivity
import co.cesarnorena.pokedex.data.model.PokedexEntry
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_pokemon_list.noInternetMessage
import kotlinx.android.synthetic.main.fragment_pokemon_list.pokemonListView
import javax.inject.Inject

class PokemonListFragment : DaggerFragment(), PokemonListContract.View {

    private val homeActivity get() = activity as HomeActivity?

    @Inject
    lateinit var presenter: PokemonListContract.Presenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_pokemon_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onCreateView(this)
    }

    override fun onDestroyView() {
        presenter.onDestroyView()
        super.onDestroyView()
    }

    override fun setupList(pokemonList: List<PokedexEntry>) {
        val context: Context = activity ?: return

        pokemonListView.layoutManager = LinearLayoutManager(context)
        pokemonListView.adapter = PokemonListAdapter(context, pokemonList) {
            presenter.onPokemonItemClick(it.number)
        }
    }

    override fun showNoInternetMessage(show: Boolean) {
        noInternetMessage.visibility = if (show) View.VISIBLE else View.GONE
        pokemonListView.visibility = if (show) View.GONE else View.VISIBLE
    }

    override fun navigatePokemonDetail(pokemonId: Int) {
        homeActivity?.showPokemonDetail(pokemonId)
    }
}
