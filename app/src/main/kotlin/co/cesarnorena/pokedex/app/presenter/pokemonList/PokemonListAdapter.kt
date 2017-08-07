package co.cesarnorena.pokedex.app.presenter.pokemonList

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import co.cesarnorena.pokedex.R
import co.cesarnorena.pokedex.data.model.PokedexEntry

class PokemonListAdapter(val pokemonList: List<PokedexEntry>) : RecyclerView.Adapter<PokemonListAdapter.ViewHolder>() {

    override fun getItemCount(): Int = pokemonList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_pokemon_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pokemon = pokemonList[position]

        holder.numberView.text = pokemon.number.toString()
        holder.nameView.text = pokemon.specie.name
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        @BindView(R.id.pokemon_row_number)
        lateinit var numberView: TextView
        @BindView(R.id.pokemon_row_name)
        lateinit var nameView: TextView

        init {
            ButterKnife.bind(this, view)
        }
    }
}