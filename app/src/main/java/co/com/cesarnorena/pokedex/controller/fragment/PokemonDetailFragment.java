package co.com.cesarnorena.pokedex.controller.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import co.com.cesarnorena.pokedex.MyApplication;
import co.com.cesarnorena.pokedex.R;
import co.com.cesarnorena.pokedex.controller.CustomAlertDialog;
import co.com.cesarnorena.pokedex.controller.activity.MainActivity;
import co.com.cesarnorena.pokedex.model.Pokemon;
import co.com.cesarnorena.pokedex.restService.PokemonServices;
import co.com.cesarnorena.pokedex.restService.RestClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Cesar on 16/01/2016.
 * <p/>
 * Fragmento que controla la vita de Detalles del Pokemon
 */
public class PokemonDetailFragment extends Fragment {

    private Context ctx;

    private View progress;
    private ImageView imageV;
    private TextView nameV;
    private TextView genderV;
    private TextView nationalIdV;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(R.layout.fragment_pokemon_detail, container, false);
        Bundle args = getArguments();

        ctx = getActivity().getApplicationContext();

        android.support.v7.app.ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            setHasOptionsMenu(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        progress = viewRoot.findViewById(R.id.pokemon_detail_progress);
        imageV = (ImageView) viewRoot.findViewById(R.id.pokemon_detail_image);
        nameV = (TextView) viewRoot.findViewById(R.id.pokemon_detail_name);
        genderV = (TextView) viewRoot.findViewById(R.id.pokemon_detail_gender);
        nationalIdV = (TextView) viewRoot.findViewById(R.id.pokemon_detail_national_id);

        if (args != null) {
            String resourceUri = args.getString("resourceUri", null);
            attemptGetPokemon(resourceUri);
        }

        return viewRoot;
    }

    /**
     * Verifica que haya conexión a internet antes de solicitar los detalles
     * del Pokemon al servidor
     *
     * @param resourceUri recurso para obtener los datos
     */
    private void attemptGetPokemon(final String resourceUri) {
        showProgress(true);

        if (MyApplication.isConnected(ctx))
            getPokemon(resourceUri);
        else {
            showProgress(false);

            CustomAlertDialog.create(getString(R.string.alert_no_connection), new CustomAlertDialog.OnDismissListener() {
                @Override
                public void onDismiss() {
                    attemptGetPokemon(resourceUri);
                }
            }).show(getFragmentManager(), null);
        }
    }

    /**
     * Obtiene los datos del Pokemon seleccionado haciendo un llamado GET al api de
     * pokeapi.co y encapsula los datos en el modelo Pokemon
     *
     * @param resourceUri recurso para obtener los datos
     */
    private void getPokemon(String resourceUri) {
        showProgress(true);

        PokemonServices pokemonService = RestClient.getRetrofit().create(PokemonServices.class);
        Call<Pokemon> call = pokemonService.getPokemon(resourceUri);

        call.enqueue(new Callback<Pokemon>() {
            @Override
            public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                if (isAdded()) {
                    Pokemon pokemon = response.body();
                    //getSprites(pokemon);

                    showProgress(false);
                    updateView(pokemon);
                }
            }

            @Override
            public void onFailure(Call<Pokemon> call, Throwable t) {
                Log.e(getTag(), "onFailure() called with: " + "t = [" + t + "]");
                if (isAdded())
                    showProgress(false);
            }
        });
    }

    /**
     * actualiza la vista una vez obentidos los datos del Pokemon
     *
     * @param pokemon Info del Pokemon
     */
    private void updateView(Pokemon pokemon) {
        showProgress(false);

        Picasso.with(ctx)
                .load(pokemon.getImageUrl())
                .into(imageV);

        nameV.setText(String.format(getString(R.string.pokemon_name), pokemon.getName()));

        nationalIdV.setText(String.format(getString(R.string.pokemon_national_id),
                String.valueOf(pokemon.getId())));

        /*if (pokemon.getGender() != null)
            genderV.setText(String.format(getString(R.string.pokemon_gender),
                    pokemon.getGender()));
        else
            genderV.setText(String.format(getString(R.string.pokemon_gender),
                    "M / F"));*/
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                MainActivity main = (MainActivity) getActivity();
                main.onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showProgress(boolean isVisible) {
        progress.setVisibility(isVisible ? View.VISIBLE : View.GONE);
    }
}
