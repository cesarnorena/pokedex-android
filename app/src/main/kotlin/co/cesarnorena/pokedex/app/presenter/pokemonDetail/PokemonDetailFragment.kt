package co.cesarnorena.pokedex.app.presenter.pokemonDetail

import android.app.Fragment
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import co.cesarnorena.pokedex.R
import co.cesarnorena.pokedex.app.presenter.MainActivity
import co.cesarnorena.pokedex.data.model.Pokemon
import co.cesarnorena.pokedex.data.remote.PokemonService
import co.cesarnorena.pokedex.data.remote.client.ServiceFactory
import co.cesarnorena.pokedex.data.repository.PokemonRepository
import co.cesarnorena.pokedex.domain.interactors.GetPokemon
import com.bumptech.glide.Glide

class PokemonDetailFragment : Fragment(), PokemonDetailContract.View {

    @BindView(R.id.pokemon_detail_toolbar)
    lateinit var toolbar: Toolbar

    @BindView(R.id.pokemon_detail_progress)
    lateinit var progress: ProgressBar

    @BindView(R.id.pokemon_detail_image)
    lateinit var pokemonImage: ImageView

    @BindView(R.id.pokemon_detail_number)
    lateinit var pokemonNumber: TextView

    @BindView(R.id.pokemon_detail_name)
    lateinit var pokemonName: TextView

    lateinit var presenter: PokemonDetailContract.Presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_pokemon_detail, container, false)
        ButterKnife.bind(this, view)
        setupToolbar()
        setupInjection()
        val id = arguments.getInt(Pokemon.ID)
        presenter.onCreateView(id)
        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            (activity as MainActivity).onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupToolbar() {
        with(activity as MainActivity) {
            setHasOptionsMenu(true)
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun setupInjection() {
        val pokemonService = ServiceFactory.create(PokemonService::class.java, PokemonService.BASE_URL)
        val remote = PokemonRepository(pokemonService)
        val getPokemon = GetPokemon(remote)
        presenter = PokemonDetailPresenter(this, getPokemon)
    }

    override fun updatePokemonData(pokemon: Pokemon) {
        Glide.with(this).load(pokemon.imageUrl).into(pokemonImage)
        pokemonNumber.text = getFormatNumber(pokemon.id)
        pokemonName.text = pokemon.name
    }

    override fun showProgress(show: Boolean) {
        progress.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun getFormatNumber(number: Int): String {
        return String.format(resources.getString(R.string.pokemon_number), Pokemon.getFormattedId(number))
    }
}