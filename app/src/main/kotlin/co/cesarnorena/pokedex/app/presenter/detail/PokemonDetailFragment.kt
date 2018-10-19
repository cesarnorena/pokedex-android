package co.cesarnorena.pokedex.app.presenter.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import co.cesarnorena.pokedex.R
import co.cesarnorena.pokedex.app.extensions.formattedId
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

class PokemonDetailFragment : DaggerFragment(), PokemonDetailContract.View {

    @Inject
    lateinit var presenter: PokemonDetailContract.Presenter

    private val pokemonId: Int by lazy {
        arguments?.getInt(POKEMON_ID) ?: throw IllegalArgumentException()
    }

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
        return inflater.inflate(R.layout.fragment_pokemon_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        presenter.onCreateView(this, pokemonId)

        nextPokemon.setOnClickListener {
            showAnotherPokemon(pokemonId + 1)
        }
        previousPokemon.setOnClickListener {
            showAnotherPokemon(pokemonId - 1)
        }
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
        toolbar.setNavigationIcon(R.drawable.ic_close)
        toolbar.setNavigationOnClickListener {
            activity?.onBackPressed()
        }
    }

    override fun updatePokemonData(pokemon: Pokemon) {
        Glide.with(this).load(pokemon.imageUrl).into(pokemonImage)
        pokemonNumber.text = getFormatNumber(pokemon.id)
        pokemonName.text = pokemon.name.capitalize()
        toolbar.title = pokemon.name.capitalize()
    }

    override fun showProgress(show: Boolean) {
        progress.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun showAnotherPokemon(pokemonId: Int) {
        (activity as HomeActivity).changePokemonDetail(pokemonId)
    }

    private fun getFormatNumber(number: Int): String {
        return getString(R.string.pokemon_number, number.formattedId())
    }
}
