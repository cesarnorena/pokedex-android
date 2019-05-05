package co.cesarnorena.pokedex.app.presenter.splash

import co.cesarnorena.pokedex.app.libraries.reactivex.addDisposeBag
import co.cesarnorena.pokedex.domain.usecases.FetchNationalPokedex
import co.cesarnorena.pokedex.domain.usecases.IsPokedexStored
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SplashPresenter @Inject constructor(
    private val isPokedexStored: IsPokedexStored,
    private val fetchPokedex: FetchNationalPokedex
) {
    private var view: SplashView? = null

    private var disposeBag = CompositeDisposable()

    fun onCreateView() {
        checkPreviousData()
    }

    fun onDestroyView() {
        disposeBag.dispose()
    }

    private fun checkPreviousData() {
        isPokedexStored()
            .delay(1000, TimeUnit.MILLISECONDS)
            .subscribe({
                view?.navigateToHomeScreen()
                view?.finishView()
            }, {
                getPokemonList()
            }).addDisposeBag(disposeBag)
    }

    private fun getPokemonList() {
        fetchPokedex(1).subscribe({
            view?.navigateToHomeScreen()
            view?.finishView()
        }, { error ->
            error.printStackTrace()
        }).addDisposeBag(disposeBag)
    }

    fun setView(view: SplashView?) {
        this.view = view
    }
}
