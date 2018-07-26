package co.cesarnorena.pokedex.app.presenter.splash

import android.content.Intent
import android.os.Bundle
import co.cesarnorena.pokedex.R
import co.cesarnorena.pokedex.app.presenter.home.HomeActivity
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class SplashActivity : DaggerAppCompatActivity(), SplashContract.View {

    @Inject
    lateinit var presenter: SplashContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activiy_splash)
        presenter.onCreateView(this)
    }

    override fun onDestroy() {
        presenter.onDestroyView()
        super.onDestroy()
    }

    override fun navigateToPokemonList() {
        startActivity(Intent(this@SplashActivity, HomeActivity::class.java))
        finish()
    }
}
