package co.cesarnorena.pokedex.app.libraries.injection

import android.app.Application
import co.cesarnorena.pokedex.app.PokedexApplication
import co.cesarnorena.pokedex.app.libraries.injection.module.ActivityBindingModule
import co.cesarnorena.pokedex.app.libraries.injection.module.ApplicationModule
import co.cesarnorena.pokedex.app.libraries.injection.module.FragmentBindingModule
import co.cesarnorena.pokedex.app.presenter.home.HomeActivityUiTest
import co.cesarnorena.pokedex.data.repository.local.PokemonDao
import co.cesarnorena.pokedex.data.repository.remote.PokemonService
import co.cesarnorena.pokedex.domain.repository.LocalRepository
import co.cesarnorena.pokedex.domain.repository.RemoteRepository
import com.nhaarman.mockitokotlin2.mock
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    LocalModuleTest::class,
    RemoteModuleTest::class,
    ActivityBindingModule::class,
    FragmentBindingModule::class,
    ApplicationModule::class,
    AndroidSupportInjectionModule::class
])
interface PokedexComponentTest : AndroidInjector<PokedexApplication> {

    fun inject(test: HomeActivityUiTest)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): PokedexComponentTest
    }
}

@Module
class LocalModuleTest {

    @Singleton
    @Provides
    fun providePokemonDao(): PokemonDao = mock()

    @Singleton
    @Provides
    fun provideLocalRepository(): LocalRepository = mock()
}

@Module
class RemoteModuleTest {

    @Singleton
    @Provides
    fun providePokemonService(): PokemonService = mock()

    @Singleton
    @Provides
    fun provideRemoteService(): RemoteRepository = mock()
}
