package co.cesarnorena.pokedex.app

import co.cesarnorena.pokedex.app.libraries.injection.DaggerPokedexComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class PokedexApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerPokedexComponent.builder()
            .application(this)
            .build()
    }
}