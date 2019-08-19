package co.cesarnorena.pokedex.app.presenter.home.list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import co.cesarnorena.pokedex.R
import co.cesarnorena.pokedex.app.presenter.home.HomeActivity
import co.cesarnorena.pokedex.data.model.PokedexEntry
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_pokemon_list.noInternetMessage
import kotlinx.android.synthetic.main.fragment_pokemon_list.pokemonListView
import javax.inject.Inject

interface PokemonListView {
    fun setupList(pokemonList: List<PokedexEntry>)
    fun showPokemonDetailScreen(pokemonId: Int)
    fun showNoInternetError(isShown: Boolean)
}

class PokemonListFragment : DaggerFragment(), PokemonListView {

    @Inject
    lateinit var presenter: PokemonListPresenter

    private val homeActivity get() = activity as HomeActivity?

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_pokemon_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.setView(this)
        presenter.onCreateView()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        presenter.setView(null)
        super.onSaveInstanceState(outState)
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

    override fun showPokemonDetailScreen(pokemonId: Int) {
        homeActivity?.showPokemonDetail(pokemonId)
    }

    override fun showNoInternetError(isShown: Boolean) {
        noInternetMessage.visibility = if (isShown) View.VISIBLE else View.GONE
        pokemonListView.visibility = if (isShown) View.GONE else View.VISIBLE
    }
}
