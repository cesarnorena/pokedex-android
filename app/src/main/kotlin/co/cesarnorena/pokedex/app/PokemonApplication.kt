package co.cesarnorena.pokedex.app

import android.app.Application
import io.realm.Realm

class PokemonApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initRealm()
    }

    private fun initRealm() {
        Realm.init(this)
    }

}
