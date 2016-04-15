package co.com.cesarnorena.pokedex.controller.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import co.com.cesarnorena.pokedex.R;
import co.com.cesarnorena.pokedex.model.Pokedex;
import co.com.cesarnorena.pokedex.model.Pokemon;

/**
 * Created by Cesar Norena on 16/01/2016.
 */
public class PokedexArrayAdapter extends ArrayAdapter<Pokedex.PokedexEntry> {

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
            holder = new PokemonHolder(row);

            row.setTag(holder);
        } else
            holder = (PokemonHolder) row.getTag();

        Pokedex.PokedexEntry pokemon = getItem(position);
        String imageUrl= pokemon.getImageUrl();

        int size = (int) context.getResources().getDimension(R.dimen.row_image);
        Picasso.with(context)
                .load(imageUrl)
                .resize(size, size)
                .into(holder.imageV);

        holder.numberV.setText(String.format(context.getString(R.string.pokemon_national_id),
                Pokemon.getFormattedId(pokemon.getNumber())));

        String name = pokemon.getSpecie().getName();
        holder.nameV.setText(name.substring(0, 1).toUpperCase() + name.substring(1));

        return row;
    }

    public class PokemonHolder {
        @Bind(R.id.pokemon_row_image)
        ImageView imageV;

        @Bind(R.id.pokemon_row_number)
        TextView numberV;

        @Bind(R.id.pokemon_row_name)
        TextView nameV;

        public PokemonHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
