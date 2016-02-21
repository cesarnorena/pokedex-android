package co.com.cesarnorena.pokedex.restService;

import co.com.cesarnorena.pokedex.model.Pokemon;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by Cesar on 16/01/2016.
 * Comsumo de servicios web ofrecidos por PokeApi.co
 */
public interface PokemonServices {

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
