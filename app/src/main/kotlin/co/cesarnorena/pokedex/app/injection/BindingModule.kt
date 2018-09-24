package co.cesarnorena.pokedex.app.injection

import co.cesarnorena.pokedex.app.presenter.home.HomeActivity
import co.cesarnorena.pokedex.app.presenter.home.injection.HomeModule
import co.cesarnorena.pokedex.app.presenter.splash.SplashActivity
import co.cesarnorena.pokedex.app.presenter.splash.injection.SplashModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = [SplashModule::class])
    abstract fun splashActivity(): SplashActivity

    @ActivityScoped
    @ContributesAndroidInjector(modules = [HomeModule::class])
    abstract fun homeActivity(): HomeActivity
}

@Module
abstract class FragmentBindingModule
