package co.com.cesarnorena.pokedex.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Cesar Norena on 16/01/2016.
 * Modelo encargado de manejar la los datos relacionados a un Pokemon y creado
 * segun los parametros obtenidos de PokeApi.co
 */
public class Pokemon {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("resource_uri")
    private String resourceUri;

    private String imageUrl;

    public Pokemon(int id, String name, String resourceUri, String imageUrl) {
        this.id = id;
        this.name = name;
        this.resourceUri = resourceUri;
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
            this.imageUrl = "http://assets.pokemon.com/assets/cms2/img/pokedex/detail/"
                    + getStringId() + ".png";

        return imageUrl;
    }

    private String getStringId() {
        String formattedId;

        if (id < 10)
            formattedId = "00" + id;
        else if (id < 100)
            formattedId = "0" + id;
        else
            formattedId = String.valueOf(id);

        return formattedId;
    }

    //---------------------------------------------------------------------

    /**
     * Modelo para manejar los Sprites correspondientes de cada Pokemon
     */
    public class Sprite {
        @SerializedName("resource_uri")
        private String resourceUtl;

        @SerializedName("image")
        private String imageUrl;

        public Sprite(String resourceUtl) {
            this.resourceUtl = resourceUtl;
        }

        public Sprite(String resourceUtl, String imageUrl) {
            this.resourceUtl = resourceUtl;
            this.imageUrl = imageUrl;
        }

        public String getResourceUtl() {
            return resourceUtl;
        }

        public String getImageUrl() {
            return imageUrl;
        }
    }
}
