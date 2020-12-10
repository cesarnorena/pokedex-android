package com.cesarnorena.pokedex.app

import com.cesarnorena.pokedex.app.libraries.injection.DaggerPokedexComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class PokedexApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<PokedexApplication> {
        return DaggerPokedexComponent.builder()
            .application(this)
            .build()
    }
}
