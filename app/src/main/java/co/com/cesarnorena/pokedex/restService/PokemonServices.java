package co.com.cesarnorena.pokedex.restService;

import co.com.cesarnorena.pokedex.model.Pokemon;
import co.com.cesarnorena.pokedex.model.PokemonList;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Url;

/**
 * Created by Cesar on 16/01/2016.
 * Comsumo de servicios web ofrecidos por PokeApi.co
 */
public interface PokemonServices {

    /**
     * @return A Pokedex returns the names and resource_uri for all pokemon
     */
    @GET("api/v1/pokedex/1/")
    Call<PokemonList> getPokemonList();

    /**
     * @param url resource url
     * @return A Pokemon resource represent a single Pokémon
     */
    @GET
    Call<Pokemon> getPokemon(@Url String url);

    /**
     * @param url resource url
     * @return A Sprite resource represent a single Pokémon Sprite
     */
    @GET
    Call<Pokemon.Sprite> getSprite(@Url String url);
}
