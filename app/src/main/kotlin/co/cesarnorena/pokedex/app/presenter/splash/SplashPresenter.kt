package co.cesarnorena.pokedex.app.presenter.splash

import co.cesarnorena.pokedex.domain.interactors.CkeckDatabase
import co.cesarnorena.pokedex.domain.interactors.GetPokedex
import io.reactivex.disposables.Disposable

class SplashPresenter(private val view: SplashContract.View,
                      private val checkDatabase: CkeckDatabase,
                      private val getPokedex: GetPokedex) : SplashContract.Presenter {

    private var mDisposable: Disposable? = null

    override fun onCreateView() {
        getPokedex()
    }

    override fun onDestroyView() {
        mDisposable?.dispose()
    }

    private fun getPokedex() {
        mDisposable = checkDatabase.execute().subscribe(
                {
                    view.navigateToPokemonList()
                },
                {
                    val request = GetPokedex.Input(1)
                    getPokedex.execute(request).subscribe(
                            { _ ->
                                view.navigateToPokemonList()
                            },
                            { error ->
                                error.printStackTrace()
                            })
                }
        )
    }

}