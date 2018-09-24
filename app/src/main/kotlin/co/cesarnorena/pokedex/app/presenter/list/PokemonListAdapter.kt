package co.cesarnorena.pokedex.app.presenter.list

import android.content.Context
import android.content.res.Resources
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import co.cesarnorena.pokedex.R
import co.cesarnorena.pokedex.data.model.PokedexEntry
import co.cesarnorena.pokedex.data.model.Pokemon
import com.bumptech.glide.Glide

class PokemonListAdapter(private val context: Context, private val pokemonList: List<PokedexEntry>)
    : RecyclerView.Adapter<PokemonListAdapter.ViewHolder>() {

    private val resources: Resources = context.resources
    private var onItemClick: ((PokedexEntry) -> Any)? = null

    override fun getItemCount(): Int = pokemonList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pokemon_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pokemon = pokemonList[position]

        with(holder) {
            numberView.text = getFormatNumber(pokemon.number)
            nameView.text = pokemon.specie.name
            containerView.setOnClickListener { onItemClick?.invoke(pokemon) }
            Glide.with(context).load(pokemon.imageUrl).into(imageView)
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        @BindView(R.id.pokemon_row_container)
        lateinit var containerView: View

        @BindView(R.id.pokemon_row_image)
        lateinit var imageView: ImageView

        @BindView(R.id.pokemon_row_number)
        lateinit var numberView: TextView

        @BindView(R.id.pokemon_row_name)
        lateinit var nameView: TextView

        init {
            ButterKnife.bind(this, view)
        }
    }

    private fun getFormatNumber(number: Int): String {
        val format = resources.getString(R.string.pokemon_number)
        return String.format(format, Pokemon.getFormattedId(number))
    }

    fun onItemClick(listener: (PokedexEntry) -> Any) {
        onItemClick = listener
    }
}
