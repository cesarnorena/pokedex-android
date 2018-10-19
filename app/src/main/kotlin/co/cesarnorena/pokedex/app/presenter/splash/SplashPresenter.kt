package co.cesarnorena.pokedex.app.presenter.splash

import co.cesarnorena.pokedex.domain.usecases.FetchPokedex
import co.cesarnorena.pokedex.domain.usecases.HasPokemonListStored
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SplashPresenter @Inject constructor(
    private val hasPokemonListStored: HasPokemonListStored,
    private val fetchPokedex: FetchPokedex
) : SplashContract.Presenter {

    private var view: SplashContract.View? = null
    private var mDisposable = CompositeDisposable()

    override fun onCreateView(view: SplashContract.View) {
        this.view = view
        checkPreviousData()
    }

    override fun onDestroyView() {
        mDisposable.dispose()
    }

    private fun checkPreviousData() {
        hasPokemonListStored()
            .delay(1000, TimeUnit.MILLISECONDS)
            .subscribe({
                view?.navigateToPokemonList()
            }, {
                getPokemonList()
            }).also {
                mDisposable.add(it)
            }
    }

    private fun getPokemonList() {
        fetchPokedex(1).subscribe({
            view?.navigateToPokemonList()
        }, { error ->
            error.printStackTrace()
        }).also {
            mDisposable.add(it)
        }
    }
}
