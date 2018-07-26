package co.cesarnorena.pokedex.app.presenter.splash

import co.cesarnorena.pokedex.domain.interactors.CheckDatabase
import co.cesarnorena.pokedex.domain.interactors.GetPokedex
import io.reactivex.disposables.CompositeDisposable

class SplashPresenter(private val view: SplashContract.View,
                      private val checkDatabase: CheckDatabase,
                      private val getPokedex: GetPokedex) : SplashContract.Presenter {

    private var mDisposable = CompositeDisposable()

    override fun onCreateView() {
        checkDatabase()
    }

    override fun onDestroyView() {
        mDisposable.dispose()
    }

    private fun checkDatabase() {
        checkDatabase.execute()
                .subscribe({
                    view.navigateToPokemonList()
                }, {
                    getPokedex()
                }).also {
                    mDisposable.add(it)
                }
    }

    private fun getPokedex() {
        getPokedex.execute(1)
                .subscribe({ _ ->
                    view.navigateToPokemonList()
                }, { error ->
                    error.printStackTrace()
                }).also {
                    mDisposable.add(it)
                }
    }
}