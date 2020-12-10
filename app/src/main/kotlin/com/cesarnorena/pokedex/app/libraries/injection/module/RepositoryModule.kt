package com.cesarnorena.pokedex.app.libraries.injection.module

import android.content.Context
import com.cesarnorena.pokedex.BuildConfig.BASE_URL
import com.cesarnorena.pokedex.data.repository.DefaultLocalRepository
import com.cesarnorena.pokedex.data.repository.DefaultRemoteRepository
import com.cesarnorena.pokedex.data.repository.local.PokedexDatabase
import com.cesarnorena.pokedex.data.repository.local.PokemonDao
import com.cesarnorena.pokedex.data.repository.remote.PokemonService
import com.cesarnorena.pokedex.data.repository.remote.client.createRemoteService
import com.cesarnorena.pokedex.domain.repository.LocalRepository
import com.cesarnorena.pokedex.domain.repository.RemoteRepository
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
