package co.cesarnorena.pokedex.app.presenter.splash

import co.cesarnorena.pokedex.domain.usecases.FetchPokedex
import co.cesarnorena.pokedex.domain.usecases.IsPokedexStored
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SplashPresenter @Inject constructor(
    private val isPokedexStored: IsPokedexStored,
    private val fetchPokedex: FetchPokedex
) {

    private var view: SplashView? = null
    private var disposable = CompositeDisposable()

    fun onCreateView() {
        checkPreviousData()
    }

    fun onDestroyView() {
        disposable.dispose()
    }

    private fun checkPreviousData() {
        isPokedexStored()
            .delay(1000, TimeUnit.MILLISECONDS)
            .subscribe({
                view?.navigateToHomeScreen()
                view?.finishView()
            }, {
                getPokemonList()
            }).also {
                disposable.add(it)
            }
    }

    private fun getPokemonList() {
        fetchPokedex(1).subscribe({
            view?.navigateToHomeScreen()
            view?.finishView()
        }, { error ->
            error.printStackTrace()
        }).also {
            disposable.add(it)
        }
    }

    fun setView(view: SplashView?) {
        this.view = view
    }
}
