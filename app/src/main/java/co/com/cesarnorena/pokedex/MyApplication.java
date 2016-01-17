package co.com.cesarnorena.pokedex;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Cesar on 16/01/2016.
 */
public class MyApplication extends Application {

    /**
     * Verifica si hay acceso a internet
     *
     * @param ctx
     * @return retorna true hay conexion a internet
     */
    public static boolean isConnected(Context ctx) {
        ConnectivityManager cm =
                (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}
