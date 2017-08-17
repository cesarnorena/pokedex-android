package co.cesarnorena.pokedex.app.presenter.pokemonDetail

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import co.cesarnorena.pokedex.R
import co.cesarnorena.pokedex.data.model.Pokemon
import co.cesarnorena.pokedex.data.remote.PokemonService
import co.cesarnorena.pokedex.data.remote.client.ServiceFactory
import co.cesarnorena.pokedex.data.repository.PokemonRepository
import co.cesarnorena.pokedex.domain.interactors.GetPokemon
import com.bumptech.glide.Glide
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PokemonDetailFragment : Fragment(), PokemonDetailContract.View {

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
        setupInjection()
        val id = arguments.getInt(Pokemon.ID)
        presenter.onCreateView(id)
        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    private fun setupInjection() {
        val remote = PokemonRepository(ServiceFactory.create(PokemonService::class.java,
                PokemonService.BASE_URL))
        val subscribeOn = Schedulers.io()
        val observeOn = AndroidSchedulers.mainThread()
        val getPokemon = GetPokemon(remote, subscribeOn, observeOn)
        presenter = PokemonDetailPresenter(this, getPokemon)
    }

    override fun updatePokemonData(pokemon: Pokemon) {
        Glide.with(this)
                .load(pokemon.imageUrl)
                .into(pokemonImage)
        pokemonNumber.text = getFormarNumber(pokemon.id)
        pokemonName.text = pokemon.name
    }

    override fun showProgress(show: Boolean) {
        progress.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun getFormarNumber(number: Int): String {
        return String.format(resources.getString(R.string.pokemon_number),
                Pokemon.getFormattedId(number))
    }
}