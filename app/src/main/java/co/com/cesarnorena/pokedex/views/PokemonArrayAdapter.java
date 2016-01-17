package co.com.cesarnorena.pokedex.views;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import co.com.cesarnorena.pokedex.R;
import co.com.cesarnorena.pokedex.models.Pokemon;

/**
 * Created by Cesar on 16/01/2016.
 */
public class PokemonArrayAdapter extends ArrayAdapter<Pokemon> {

    private Context context;
    private int resource;

    public PokemonArrayAdapter(Context context, int resource) {
        super(context, resource);
        this.context= context;
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        PokemonHolder holder;

        if (row == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            row = inflater.inflate(resource, parent, false);

            holder = new PokemonHolder();
            holder.nameV = (TextView) row.findViewById(R.id.pokemonitem_name);

            row.setTag(holder);
        } else {
            holder = (PokemonHolder) row.getTag();
        }

        Pokemon pokemon = getItem(position);
        holder.nameV.setText(pokemon.getName());

        return row;
    }

    private class PokemonHolder {
        TextView nameV;
    }
}
