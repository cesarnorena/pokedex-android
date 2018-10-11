package co.cesarnorena.pokedex.app.presenter.detail

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
import co.cesarnorena.pokedex.app.presenter.home.HomeActivity
import co.cesarnorena.pokedex.data.model.Pokemon
import com.bumptech.glide.Glide
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class PokemonDetailFragment : DaggerFragment(), PokemonDetailContract.View {

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

    private val pokemonId: Int
        get() = arguments?.getInt(POKEMON_ID) ?: throw IllegalArgumentException()

    @Inject
    lateinit var presenter: PokemonDetailContract.Presenter

    companion object {
        private const val POKEMON_ID = "pokemon_id"

        fun newInstance(pokemonId: Int): PokemonDetailFragment {
            val bundle = Bundle().apply {
                putInt(POKEMON_ID, pokemonId)
            }
            return PokemonDetailFragment().apply {
                arguments = bundle
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_pokemon_detail, container, false)
        ButterKnife.bind(this, view)
        setupToolbar()
        presenter.onCreateView(this, pokemonId)
        return view
    }

    override fun onDestroyView() {
        presenter.onDestroyView()
        super.onDestroyView()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            (activity as HomeActivity).onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupToolbar() {
        with(activity as HomeActivity) {
            setHasOptionsMenu(true)
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
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
        val format = resources.getString(R.string.pokemon_number)
        return String.format(format, Pokemon.getFormattedId(number))
    }
}
