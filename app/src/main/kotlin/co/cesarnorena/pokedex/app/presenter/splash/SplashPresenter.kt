package co.cesarnorena.pokedex.app.presenter.splash

import co.cesarnorena.pokedex.domain.interactors.CkeckDatabase
import co.cesarnorena.pokedex.domain.interactors.GetPokedex
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

class SplashPresenter(private val view: SplashContract.View,
                      private val checkDatabase: CkeckDatabase,
                      private val getPokedex: GetPokedex) : SplashContract.Presenter {

    private var mDisposable = CompositeDisposable()

    override fun onCreateView() {
        checkDatabse().also {
            mDisposable.add(it)
        }
    }

    override fun onDestroyView() {
        mDisposable.dispose()
    }

    private fun checkDatabse(): Disposable {
        return checkDatabase.execute().subscribe(
                {
                    view.navigateToPokemonList()
                },
                {
                    getPokedex().also {
                        mDisposable.add(it)
                    }
                }
        )
    }

    private fun getPokedex(): Disposable {
        val request = GetPokedex.Input(1)
        return getPokedex.execute(request).subscribe(
                { _ ->
                    view.navigateToPokemonList()
                },
                { error ->
                    error.printStackTrace()
                })
    }

}