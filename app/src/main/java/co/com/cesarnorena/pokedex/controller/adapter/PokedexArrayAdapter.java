package co.com.cesarnorena.pokedex.controller.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import co.com.cesarnorena.pokedex.R;
import co.com.cesarnorena.pokedex.model.Pokemon;

/**
 * Created by Cesar on 16/01/2016.
 *
 * ArrayAdapter personalizado para la creacion de cada item de la
 * lista de pokemones
 */
public class PokedexArrayAdapter extends ArrayAdapter<Pokemon> {

    private Context context;
    private int resource;

    public PokedexArrayAdapter(Context context, int resource) {
        super(context, resource);
        this.context = context;
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
