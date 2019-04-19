package co.cesarnorena.pokedex.app.libraries.injection

import android.app.Application
import co.cesarnorena.pokedex.app.PokedexApplication
import co.cesarnorena.pokedex.app.libraries.injection.module.ActivityBindingModule
import co.cesarnorena.pokedex.app.libraries.injection.module.ApplicationModule
import co.cesarnorena.pokedex.app.libraries.injection.module.FragmentBindingModule
import co.cesarnorena.pokedex.app.libraries.injection.module.LocalModule
import co.cesarnorena.pokedex.app.libraries.injection.module.RemoteModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    RemoteModule::class,
    LocalModule::class,
    ActivityBindingModule::class,
    FragmentBindingModule::class,
    ApplicationModule::class,
    AndroidSupportInjectionModule::class
])
interface PokedexComponent : AndroidInjector<PokedexApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): PokedexComponent
    }
}
