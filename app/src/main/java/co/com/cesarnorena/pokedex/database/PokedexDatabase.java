package co.com.cesarnorena.pokedex.database;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by Cesar Norena on 29/02/2016.
 */
@Database(name = PokedexDatabase.NAME, version = PokedexDatabase.VERSION)
public class PokedexDatabase {

    public static final String NAME = "pokedexapp";
    public static final int VERSION = 1;
}
