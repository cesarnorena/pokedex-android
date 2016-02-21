package co.com.cesarnorena.pokedex.controller.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import co.com.cesarnorena.pokedex.MyApplication;
import co.com.cesarnorena.pokedex.R;
import co.com.cesarnorena.pokedex.controller.CustomAlertDialog;
import co.com.cesarnorena.pokedex.controller.adapter.PokedexArrayAdapter;
import co.com.cesarnorena.pokedex.controller.activity.MainActivity;
import co.com.cesarnorena.pokedex.model.Pokedex;
import co.com.cesarnorena.pokedex.restService.PokedexServices;
import co.com.cesarnorena.pokedex.restService.RestClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by diana on 16/01/2016.
 *
 * Fragmento que controla la vista Lista de Pokemones
 */
public class PokemonListFragment extends Fragment implements AdapterView.OnItemClickListener {

    private Context ctx;
    private PokedexArrayAdapter adapter;
    private ListView pokemonListV;
    private View progress;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(R.layout.fragment_pokemon_list, container, false);
        ctx = getActivity().getApplicationContext();

        android.support.v7.app.ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            setHasOptionsMenu(false);
            actionBar.setDisplayHomeAsUpEnabled(false);
        }

        progress = viewRoot.findViewById(R.id.pokemon_list_progress);

        pokemonListV = (ListView) viewRoot.findViewById(R.id.pokemon_list_listview);
        pokemonListV.setOnItemClickListener(this);

        if (adapter == null || adapter.getCount() == 0) {
            adapter = new PokedexArrayAdapter(ctx, R.layout.row_pokemon_list);
            attemptGetPokemonList();

        } else {
            showProgress(false);
            pokemonListV.setAdapter(adapter);
        }

        return viewRoot;
    }

    /**
     * Verifica que haya conexión a internet antes de solicitar la lista
     * de Pokemones al servidor
     */
    private void attemptGetPokemonList() {
        showProgress(true);

        if (MyApplication.isConnected(ctx))
            getPokemonList();
        else {
            showProgress(false);

            CustomAlertDialog.create(getString(R.string.alert_no_connection), new CustomAlertDialog.OnDismissListener() {
                @Override
                public void onDismiss() {
                    attemptGetPokemonList();
                }
            }).show(getFragmentManager(), null);
        }
    }

    /**
     * Obtiene la lista completa de Pokemones haciendo un llamado GET al api de
     * pokeapi.co y encapsula los datos en el modelo Pokedex
     */
    private void getPokemonList() {
        PokedexServices pokemonService = RestClient.getRetrofit().create(PokedexServices.class);

        Call<Pokedex> call = pokemonService.getPokedex();

        call.enqueue(new Callback<Pokedex>() {
            @Override
            public void onResponse(Call<Pokedex> call, Response<Pokedex> response) {
                if (isAdded()) {
                    Pokedex pokemons = response.body();

                    adapter.addAll(pokemons.getPokedexEntries());
                    showProgress(false);
                    pokemonListV.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<Pokedex> call, Throwable t) {
                Log.e(getTag(), "onFailure() called with: " + "t = [" + t + "]");
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
        if (isVisible()) {
            Bundle args = new Bundle();
            args.putString("resourceUri", adapter.getItem(pos).getSpecie().getUrl());

            MainActivity main = (MainActivity) getActivity();
            main.replaceFragment(new PokemonDetailFragment(), PokemonDetailFragment.class.getSimpleName(), true, args);
        }
    }

    private void showProgress(boolean isVisible) {
        progress.setVisibility(isVisible ? View.VISIBLE : View.GONE);
    }
}
