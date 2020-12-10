package com.cesarnorena.pokedex.app.presenter.home.detail

import android.content.res.Resources
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout.LayoutParams
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.cesarnorena.pokedex.R
import com.cesarnorena.pokedex.app.libraries.extensions.formattedId
import com.cesarnorena.pokedex.app.presenter.home.HomeActivity
import com.cesarnorena.pokedex.data.model.Pokemon
import com.cesarnorena.pokedex.data.model.TypeSlot
import com.bumptech.glide.Glide
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_pokemon_detail.*
import javax.inject.Inject

interface PokemonDetailView {
    fun updatePokemonData(pokemon: Pokemon)
    fun updatePokemonTypes(types: List<TypeSlot>)
    fun nextButtonVisibility(isVisible: Boolean)
    fun previousButtonVisibility(isVisible: Boolean)
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

    override fun updatePokemonTypes(types: List<TypeSlot>) {
        typeContainer.removeAllViews()
        types.forEach {
            typeContainer.addView(typeView(it.type.name))
        }
    }

    override fun nextButtonVisibility(isVisible: Boolean) {
        nextPokemon.visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    override fun previousButtonVisibility(isVisible: Boolean) {
        previousPokemon.visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    override fun showProgress(isShown: Boolean) {
        progress.visibility = if (isShown) View.VISIBLE else View.GONE
    }

    private fun typeView(name: String): TextView {
        val context = activity ?: throw IllegalArgumentException()

        return TextView(activity).apply {
            val params = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
            params.setMargins(0, 0, 16.toPx(), 0)
            layoutParams = params

            val padding = 16.toPx()
            setPadding(padding, padding / 2, padding, padding / 2)

            setBackgroundColor(ContextCompat.getColor(context, R.color.colorSecondary))
            setTypeface(typeface, Typeface.BOLD)
            setTextColor(ContextCompat.getColor(context, android.R.color.white))
            text = name.capitalize()
        }
    }

    private fun getFormattedNumber(number: Int): String {
        return getString(R.string.pokemon_number, number.formattedId())
    }

    private fun Int.toPx(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()
}
