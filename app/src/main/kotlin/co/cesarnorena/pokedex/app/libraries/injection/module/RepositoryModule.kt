package co.cesarnorena.pokedex.app.libraries.injection.module

import android.content.Context
import co.cesarnorena.pokedex.BuildConfig.BASE_URL
import co.cesarnorena.pokedex.data.repository.DefaultLocalRepository
import co.cesarnorena.pokedex.data.repository.DefaultRemoteRepository
import co.cesarnorena.pokedex.data.repository.local.PokedexDatabase
import co.cesarnorena.pokedex.data.repository.local.PokemonDao
import co.cesarnorena.pokedex.data.repository.remote.PokemonService
import co.cesarnorena.pokedex.data.repository.remote.client.createRemoteService
import co.cesarnorena.pokedex.domain.repository.LocalRepository
import co.cesarnorena.pokedex.domain.repository.RemoteRepository
import dagger.Module
import dagger.Provides

@Module
class LocalModule {

    @Provides
    fun providePokemonDao(context: Context): PokemonDao {
        return PokedexDatabase.getPokemonDao(context)
    }

    @Provides
    fun provideLocalRepository(pokemonDao: PokemonDao): LocalRepository {
        return DefaultLocalRepository(pokemonDao)
    }
}

@Module
class RemoteModule {

    @Provides
    fun providePokemonService(): PokemonService {
        return createRemoteService(BASE_URL)
    }

    @Provides
    fun provideRemoteService(pokemonService: PokemonService): RemoteRepository {
        return DefaultRemoteRepository(pokemonService)
    }
}
