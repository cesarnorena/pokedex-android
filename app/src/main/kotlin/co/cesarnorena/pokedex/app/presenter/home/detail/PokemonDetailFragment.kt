package co.cesarnorena.pokedex.app.presenter.home.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import co.cesarnorena.pokedex.R
import co.cesarnorena.pokedex.app.libraries.extensions.formattedId
import co.cesarnorena.pokedex.app.presenter.home.HomeActivity
import co.cesarnorena.pokedex.data.model.Pokemon
import com.bumptech.glide.Glide
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_pokemon_detail.nextPokemon
import kotlinx.android.synthetic.main.fragment_pokemon_detail.pokemonImage
import kotlinx.android.synthetic.main.fragment_pokemon_detail.pokemonName
import kotlinx.android.synthetic.main.fragment_pokemon_detail.pokemonNumber
import kotlinx.android.synthetic.main.fragment_pokemon_detail.previousPokemon
import kotlinx.android.synthetic.main.fragment_pokemon_detail.progress
import kotlinx.android.synthetic.main.fragment_pokemon_detail.toolbar
import javax.inject.Inject

interface PokemonDetailView {
    fun updatePokemonData(pokemon: Pokemon)
    fun showProgress(isShown: Boolean)
}

class PokemonDetailFragment : DaggerFragment(), PokemonDetailView {

    @Inject
    lateinit var presenter: PokemonDetailPresenter

    private val homeActivity get() = activity as HomeActivity?

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
    ): View = inflater.inflate(R.layout.fragment_pokemon_detail, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        nextPokemon.setOnClickListener { presenter.onNextPokemon() }
        previousPokemon.setOnClickListener { presenter.onPreviousPokemon() }

        presenter.pokemonId = arguments?.getInt(POKEMON_ID) ?: throw IllegalArgumentException()
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            homeActivity?.onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupToolbar() {
        toolbar.setNavigationIcon(R.drawable.ic_close)
        toolbar.setNavigationOnClickListener {
            activity?.onBackPressed()
        }
    }

    override fun updatePokemonData(pokemon: Pokemon) {
        Glide.with(this).load(pokemon.imageUrl).into(pokemonImage)
        pokemonNumber.text = getFormattedNumber(pokemon.id)
        pokemonName.text = pokemon.name.capitalize()
        toolbar.title = pokemon.name.capitalize()
    }

    override fun showProgress(isShown: Boolean) {
        progress.visibility = if (isShown) View.VISIBLE else View.GONE
    }

    private fun getFormattedNumber(number: Int): String {
        return getString(R.string.pokemon_number, number.formattedId())
    }
}
