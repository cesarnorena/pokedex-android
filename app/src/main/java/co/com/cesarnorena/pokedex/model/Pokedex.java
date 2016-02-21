package co.com.cesarnorena.pokedex.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Cesar Norena on 16/01/2016.
 */
public class Pokedex {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("pokemon_entries")
    private List<PokedexEntry> pokedexEntries;

    public Pokedex(int id, String name, List<PokedexEntry> pokemons) {
        this.id = id;
        this.name = name;
        this.pokedexEntries = pokemons;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<PokedexEntry> getPokedexEntries() {
        return pokedexEntries;
    }

    public class PokedexEntry {
        @SerializedName("entry_number")
        private int number;

        @SerializedName("pokemon_species")
        private Specie specie;

        public PokedexEntry(int number, Specie specie) {
            this.number = number;
            this.specie = specie;
        }

        public int getNumber() {
            return number;
        }

        public Specie getSpecie() {
            return specie;
        }
    }

    public class Specie {
        @SerializedName("name")
        private String name;

        @SerializedName("url")
        private String url;

        public Specie(String name, String url) {
            this.name = name;
            this.url = url;
        }

        public String getName() {
            return name;
        }

        public String getUrl() {
            return url;
        }
    }
}
