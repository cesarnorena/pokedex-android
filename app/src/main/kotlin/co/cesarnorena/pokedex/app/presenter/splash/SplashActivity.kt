package co.cesarnorena.pokedex.app.presenter.splash

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import co.cesarnorena.pokedex.R
import co.cesarnorena.pokedex.app.presenter.MainActivity
import co.cesarnorena.pokedex.data.remote.PokemonService
import co.cesarnorena.pokedex.data.remote.client.ServiceFactory
import co.cesarnorena.pokedex.data.repository.PokemonRepository
import co.cesarnorena.pokedex.data.repository.RoomRepository
import co.cesarnorena.pokedex.domain.interactors.CheckDatabase
import co.cesarnorena.pokedex.domain.interactors.GetPokedex
import co.cesarnorena.pokedex.domain.repository.LocalRepository
import co.cesarnorena.pokedex.domain.repository.RemoteRepository

class SplashActivity : AppCompatActivity(), SplashContract.View {

    lateinit var presenter: SplashContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activiy_splash)
        setupInjection()
        presenter.onCreateView()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroyView()
    }

    private fun setupInjection() {
        val remote: RemoteRepository = PokemonRepository(ServiceFactory
                .create(PokemonService::class.java, PokemonService.BASE_URL))

        val local: LocalRepository = RoomRepository(this)

        val checkDatabase = CheckDatabase(local)
        val getPokedex = GetPokedex(remote, local)

        presenter = SplashPresenter(this, checkDatabase, getPokedex)
    }

    override fun navigateToPokemonList() {
        startActivity(Intent(this@SplashActivity, MainActivity::class.java))
        finish()
    }

}