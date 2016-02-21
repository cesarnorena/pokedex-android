package co.com.cesarnorena.pokedex.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Cesar on 16/01/2016.
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

    private List<Sprite> sprites;

    private String imageUrl;

    public Pokemon(int id, String name, String resourceUri, List<Sprite> sprites, String imageUrl) {
        this.id = id;
        this.name = name;
        this.resourceUri = resourceUri;
        this.sprites = sprites;
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getResourceUri() {
        return resourceUri;
    }

    public List<Sprite> getSprites() {
        return sprites;
    }

    public String getImageUrl() {
        return imageUrl;
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
