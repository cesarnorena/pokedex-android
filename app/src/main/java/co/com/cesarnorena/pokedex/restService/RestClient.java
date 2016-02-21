package co.com.cesarnorena.pokedex.restService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Cesar Norena on 16/01/2016.
 */
public class RestClient {

    /**
     * API Host
     */
    //public static final String BASE_URL = "http://pokeapi.co/api/v1/";
    public static final String BASE_URL = "http://pokeapi.co/api/v2/";

    /**
     *
     * @return Retorna una instancia del Singleton de Picasso
     */
    static public Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();
    }
}