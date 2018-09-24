package co.cesarnorena.pokedex.domain.repository.injection

import co.cesarnorena.pokedex.data.repository.DefaultLocalRepository
import co.cesarnorena.pokedex.data.repository.DefaultRemoteRepository
import co.cesarnorena.pokedex.data.repository.remote.BASE_URL
import co.cesarnorena.pokedex.data.repository.remote.PokemonService
import co.cesarnorena.pokedex.data.repository.remote.client.createRemoteService
import co.cesarnorena.pokedex.domain.repository.LocalRepository
import co.cesarnorena.pokedex.domain.repository.RemoteRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class LocalModule {

    @Binds
    abstract fun bindLocalRepository(
        defaultLocalRepository: DefaultLocalRepository
    ): LocalRepository
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
