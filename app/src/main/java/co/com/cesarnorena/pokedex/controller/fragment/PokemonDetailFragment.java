package co.com.cesarnorena.pokedex.controller.fragment;

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

import butterknife.Bind;
import butterknife.ButterKnife;
import co.com.cesarnorena.pokedex.MyApplication;
import co.com.cesarnorena.pokedex.R;
import co.com.cesarnorena.pokedex.controller.dialog.CustomAlertDialog;
import co.com.cesarnorena.pokedex.controller.activity.MainActivity;
import co.com.cesarnorena.pokedex.model.Pokemon;
import co.com.cesarnorena.pokedex.restservice.PokemonServices;
import co.com.cesarnorena.pokedex.restservice.RestClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Cesar Norena on 16/01/2016.
 */
public class PokemonDetailFragment extends BaseFragment {

    @Bind(R.id.pokemon_detail_progress)
     View progressV;

    @Bind(R.id.pokemon_detail_image)
     ImageView imageV;

    @Bind(R.id.pokemon_detail_name)
     TextView nameV;

    @Bind(R.id.pokemon_detail_number)
     TextView nationalIdV;

    private Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pokemon_detail, container, false);
        ButterKnife.bind(this, view);

        context = getActivity().getApplicationContext();

        Bundle args = getArguments();

        android.support.v7.app.ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            setHasOptionsMenu(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        if (args != null) {
            String resourceUri = args.getString("resourceUrl", null);
            attemptGetPokemon(resourceUri);
        }

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private void attemptGetPokemon(final String resourceUri) {
        showProgress(true);

        if (MyApplication.isConnected(context))
            getPokemon(resourceUri);
        else {
            showProgress(false);

            CustomAlertDialog.create(
                    getString(R.string.alert_title_remeber),
                    getString(R.string.alert_no_connection),
                    getString(R.string.alert_accept),
                    new CustomAlertDialog.OnDismissListener() {
                        @Override
                        public void onDismiss() {
                            attemptGetPokemon(resourceUri);
                        }
                    }).show(getFragmentManager(), null);
        }
    }

    private void getPokemon(final String resourceUri) {
        showProgress(true);

        PokemonServices pokemonService = RestClient.getRetrofit().create(PokemonServices.class);
        Call<Pokemon> call = pokemonService.getPokemon(resourceUri);

        call.enqueue(new Callback<Pokemon>() {
            @Override
            public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                if (isAdded()) {
                    Pokemon pokemon = response.body();
                    updateView(pokemon);
                    showProgress(false);
                }
            }

            @Override
            public void onFailure(Call<Pokemon> call, Throwable t) {
                Log.e(getTag(), "onFailure() "+ t );

                if (isVisible()) {
                    showProgress(false);
                    CustomAlertDialog.create(
                            getString(R.string.alert_title),
                            getString(R.string.alert_server_error),
                            getString(R.string.alert_accept),
                            new CustomAlertDialog.OnDismissListener() {
                                @Override
                                public void onDismiss() {
                                    attemptGetPokemon(resourceUri);
                                }
                            }).show(getFragmentManager(), null);
                }
            }
        });
    }

    private void updateView(Pokemon pokemon) {
        int size= (int)getResources().getDimension(R.dimen.detail_image);
        Picasso.with(context)
                .load(pokemon.getImageUrl())
                .resize(size, size)
                .into(imageV);

        String name = pokemon.getName();
        nameV.setText(name.substring(0, 1).toUpperCase() + name.substring(1));

        nationalIdV.setText(String.format(getString(R.string.pokemon_national_id),
                Pokemon.getFormattedId(pokemon.getId())));
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
        progressV.setVisibility(isVisible ? View.VISIBLE : View.GONE);
    }
}
