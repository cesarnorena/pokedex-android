package co.com.cesarnorena.pokedex.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Cesar on 16/01/2016.
 */
public class Pokedex {

    /*
    {
        "created": "2013-11-09T15:14:48.957604",
        "modified": "2013-11-09T15:14:48.957565",
        "name": "national",
        "pokemon": [
        {
          "name": "pidgeotto",
          "resource_uri": "api/v1/pokemon/17/"
        },
        ...
        ]
     }
     */

    @SerializedName("created")
    private String createdAt;

    @SerializedName("modified")
    private String updatedAt;

    private String name;

    @SerializedName("pokemon")
    private List<Pokemon> pokemons;

    public Pokedex(String createdAt, String updatedAt, String name, List<Pokemon> pokemons) {
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.name = name;
        this.pokemons = pokemons;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String getName() {
        return name;
    }

    public List<Pokemon> getPokemons() {
        return pokemons;
    }
}
