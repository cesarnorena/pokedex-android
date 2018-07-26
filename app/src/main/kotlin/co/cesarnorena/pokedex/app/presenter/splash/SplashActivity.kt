package co.cesarnorena.pokedex.app.presenter.splash

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import co.cesarnorena.pokedex.R
import co.cesarnorena.pokedex.app.presenter.MainActivity
import co.cesarnorena.pokedex.data.remote.BASE_URL
import co.cesarnorena.pokedex.data.remote.PokemonService
import co.cesarnorena.pokedex.data.remote.client.ServiceFactory
import co.cesarnorena.pokedex.data.repository.DefaultLocalRepository
import co.cesarnorena.pokedex.data.repository.DefaultRemoteRepository
import co.cesarnorena.pokedex.domain.interactors.CheckDatabase
import co.cesarnorena.pokedex.domain.interactors.GetPokedex

class SplashActivity : AppCompatActivity(), SplashContract.View {

    lateinit var presenter: SplashContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activiy_splash)
        setupInjection()
        presenter.onCreateView()
    }

    override fun onDestroy() {
        presenter.onDestroyView()
        super.onDestroy()
    }

    private fun setupInjection() {
        val pokemonService = ServiceFactory.create<PokemonService>(BASE_URL)
        val remoteRepository = DefaultRemoteRepository(pokemonService)
        val localRepository = DefaultLocalRepository(this)

        val checkDatabase = CheckDatabase(localRepository)
        val getPokedex = GetPokedex(remoteRepository, localRepository)

        presenter = SplashPresenter(this, checkDatabase, getPokedex)
    }

    override fun navigateToPokemonList() {
        startActivity(Intent(this@SplashActivity, MainActivity::class.java))
        finish()
    }
}