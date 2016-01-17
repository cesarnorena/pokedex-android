package co.com.cesarnorena.pokedex.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Cesar on 16/01/2016.
 */
public class Pokemon {

    private String name;

    @SerializedName("resource_uri")
    private String resourceUri;

    @SerializedName("national_id")
    private int nationalId;

    @SerializedName("male_femal_ratio ")
    private String gender;

    private List<Sprite> sprites;

    private String imageUrl;

    public Pokemon(String name, String resourceUri, int nationalId, String gender, List<Sprite> sprites) {
        this.name = name;
        this.resourceUri = resourceUri;
        this.nationalId = nationalId;
        this.gender = gender;
        this.sprites = sprites;
    }

    public Pokemon(String name, String resourceUri) {
        this.name = name;
        this.resourceUri = resourceUri;
    }

    public String getName() {
        return name;
    }

    public String getResourceUri() {
        return resourceUri;
    }

    public int getNationalId() {
        return nationalId;
    }

    public String getGender() {
        return gender;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<Sprite> getSprites() {
        return sprites;
    }

    public void setSprites(List<Sprite> sprites) {
        this.sprites = sprites;
    }

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

        public String   getImageUrl() {
            return imageUrl;
        }
    }
}
