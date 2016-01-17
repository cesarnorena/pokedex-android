package co.com.cesarnorena.pokedex.restServices;

import java.util.List;

import co.com.cesarnorena.pokedex.models.Pokemon;
import co.com.cesarnorena.pokedex.models.PokemonList;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Url;

/**
 * Created by Cesar on 16/01/2016.
 */
public interface PokemonServices {

    @GET("api/v1/pokedex/1/")
    Call<PokemonList> getPokemonList();

    @GET
    Call<Pokemon> getPokemon(@Url String url);

    @GET
    Call<Pokemon.Sprite> getSprite(@Url String url);
}
