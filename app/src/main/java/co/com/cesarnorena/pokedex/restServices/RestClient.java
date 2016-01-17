package co.com.cesarnorena.pokedex.restServices;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by Cesar on 16/01/2016.
 */
public class RestClient {

    /**
     * API Host
     */
    public static final String BASE_URL = "http://pokeapi.co/";

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