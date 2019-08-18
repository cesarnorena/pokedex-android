package co.cesarnorena.pokedex.app.presenter.splash

import android.content.Intent
import android.os.Bundle
import co.cesarnorena.pokedex.R
import co.cesarnorena.pokedex.app.presenter.home.HomeActivity
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

interface SplashView {
    fun navigateToHomeScreen()
    fun finishView()
}

class SplashActivity : DaggerAppCompatActivity(), SplashView {

    @Inject
    lateinit var presenter: SplashPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activiy_splash)
        presenter.setView(this)
        presenter.onCreateView()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        presenter.setView(null)
        super.onSaveInstanceState(outState)
    }

    override fun onDestroy() {
        presenter.onDestroyView()
        super.onDestroy()
    }

    override fun navigateToHomeScreen() {
        startActivity(Intent(this, HomeActivity::class.java))
    }

    override fun finishView() {
        finish()
    }
}
