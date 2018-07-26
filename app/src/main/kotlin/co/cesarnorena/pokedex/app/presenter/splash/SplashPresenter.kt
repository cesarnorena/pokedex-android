package co.cesarnorena.pokedex.app.presenter.splash

import co.cesarnorena.pokedex.domain.usecases.CheckDatabase
import co.cesarnorena.pokedex.domain.usecases.GetPokedex
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class SplashPresenter @Inject constructor(
        private val checkDatabase: CheckDatabase,
        private val getPokedex: GetPokedex
) : SplashContract.Presenter {

    private var view: SplashContract.View? = null
    private var mDisposable = CompositeDisposable()

    override fun onCreateView(view: SplashContract.View) {
        this.view = view
        checkDatabase()
    }

    override fun onDestroyView() {
        mDisposable.dispose()
    }

    private fun checkDatabase() {
        checkDatabase.execute()
                .subscribe({
                    view?.navigateToPokemonList()
                }, {
                    getPokedex()
                }).also {
                    mDisposable.add(it)
                }
    }

    private fun getPokedex() {
        getPokedex.execute(1)
                .subscribe({ _ ->
                    view?.navigateToPokemonList()
                }, { error ->
                    error.printStackTrace()
                }).also {
                    mDisposable.add(it)
                }
    }
}
