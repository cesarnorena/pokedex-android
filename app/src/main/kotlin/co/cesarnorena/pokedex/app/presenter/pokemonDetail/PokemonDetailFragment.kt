package co.cesarnorena.pokedex.app.presenter.pokemonDetail

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import co.cesarnorena.pokedex.R
import co.cesarnorena.pokedex.data.model.Pokemon
import co.cesarnorena.pokedex.data.remote.PokemonService
import co.cesarnorena.pokedex.data.repository.PokemonRepository
import co.cesarnorena.pokedex.domain.interactors.GetPokemon
import co.tappsi.taxidriver.data.remote.client.ServiceFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PokemonDetailFragment : Fragment(), PokemonDetailContract.View {

    lateinit var presenter: PokemonDetailContract.Presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_pokemon_detail, container, false)
        ButterKnife.bind(this, view)
        setupInjeciton()
        presenter.onCreateView(1)
        return view
    }

    fun setupInjeciton() {
        val remote = PokemonRepository(ServiceFactory.create(PokemonService::class.java,
                PokemonService.BASE_URL))
        val subscribeOn = Schedulers.io()
        val observeOn = AndroidSchedulers.mainThread()
        val getPokemon = GetPokemon(remote, subscribeOn, observeOn)
        presenter = PokemonDetailPresenter(this, getPokemon)
    }

    override fun updatePokemonData(pokemon: Pokemon) {
    }
}