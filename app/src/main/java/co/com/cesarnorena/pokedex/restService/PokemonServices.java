package co.com.cesarnorena.pokedex.restService;

import co.com.cesarnorena.pokedex.model.Pokemon;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by Cesar on 16/01/2016
 *
 */
public interface PokemonServices {

    @GET
    Call<Pokemon> getPokemon(@Url String url);
}
