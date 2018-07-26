package co.cesarnorena.pokedex.app.presenter.splash.injection

import co.cesarnorena.pokedex.app.injection.ActivityScoped
import co.cesarnorena.pokedex.app.presenter.splash.SplashContract
import co.cesarnorena.pokedex.app.presenter.splash.SplashPresenter
import dagger.Binds
import dagger.Module

@Module
abstract class SplashModule {

    @ActivityScoped
    @Binds
    abstract fun bindSplashPresenter(splashPresenter: SplashPresenter): SplashContract.Presenter
}