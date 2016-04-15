package co.com.cesarnorena.pokedex.restservice;

import co.com.cesarnorena.pokedex.model.Pokedex;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Cesar Norena on 20/02/2016.
 *
 */
public interface PokedexServices {

    @GET("pokedex/1/")
    Call<Pokedex> getPokedex();
}
