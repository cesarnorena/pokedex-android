package co.com.cesarnorena.pokedex.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Cesar Norena on 16/01/2016.
 *
 */
public class Pokemon {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    private String imageUrl;

    public Pokemon(int id, String name, String imageUrl) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        if (imageUrl == null)
            this.imageUrl = "http://assets.pokemon.com/assets/cms2/img/pokedex/full/"
                    + getFormattedId(id) + ".png";

        return imageUrl;
    }

    public static String getFormattedId(int id) {
        String formattedId;

        if (id < 10)
            formattedId = "00" + id;
        else if (id < 100)
            formattedId = "0" + id;
        else
            formattedId = String.valueOf(id);

        return formattedId;
    }
}
