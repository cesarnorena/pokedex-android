package co.cesarnorena.pokedex.app.presenter.splash

import co.cesarnorena.pokedex.domain.interactors.GetPokedex
import io.reactivex.disposables.Disposable

class SplashPresenter(private val view: SplashContract.View,
                      private val getPokedex: GetPokedex) : SplashContract.Presenter {

    private var mDisposable: Disposable? = null

    override fun onCreateView() {
    }

    override fun onDestroyView() {
    }

    private fun getPokedex() {
        val request = GetPokedex.Input(1)
        mDisposable = getPokedex.execute(request).subscribe(
                { _ ->
                    view.showPokemonList()
                },
                { error ->
                    error.printStackTrace()
                })
    }
}