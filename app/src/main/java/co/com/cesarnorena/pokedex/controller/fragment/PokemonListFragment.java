package co.com.cesarnorena.pokedex.controller.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import co.com.cesarnorena.pokedex.MyApplication;
import co.com.cesarnorena.pokedex.R;
import co.com.cesarnorena.pokedex.controller.activity.MainActivity;
import co.com.cesarnorena.pokedex.controller.adapter.PokedexArrayAdapter;
import co.com.cesarnorena.pokedex.controller.dialog.CustomAlertDialog;
import co.com.cesarnorena.pokedex.model.Pokedex;
import co.com.cesarnorena.pokedex.restservice.PokedexServices;
import co.com.cesarnorena.pokedex.restservice.RestClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Cesar Norena on 16/01/2016.
 */
public class PokemonListFragment extends BaseFragment {

    @Bind(R.id.pokemon_list_listview)
    ListView pokemonListV;

    @Bind(R.id.pokemon_list_progress)
    ProgressBar progressV;

    private PokedexArrayAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pokemon_list, container, false);
        ButterKnife.bind(this, view);

        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            setHasOptionsMenu(false);
            actionBar.setDisplayHomeAsUpEnabled(false);
        }

        if (adapter == null || adapter.getCount() == 0) {
            adapter = new PokedexArrayAdapter(getActivity(), R.layout.row_pokemon_list);
            attemptGetPokemonList();

        } else {
            showProgress(false);
            pokemonListV.setAdapter(adapter);
        }

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private void attemptGetPokemonList() {
        showProgress(true);

        if (MyApplication.isConnected(getActivity()))
            getPokemonList();
        else {
            showProgress(false);

            new MaterialDialog.Builder(getActivity())
                    .title(R.string.alert_title_remeber)
                    .content(R.string.alert_no_connection)
                    .positiveText(R.string.alert_accept)
                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            attemptGetPokemonList();
                        }
                    }).show();
        }
    }

    private void getPokemonList() {
        PokedexServices pokemonService = RestClient.getRetrofit().create(PokedexServices.class);

        Call<Pokedex> call = pokemonService.getPokedex();
        call.enqueue(new Callback<Pokedex>() {
            @Override
            public void onResponse(Call<Pokedex> call, Response<Pokedex> response) {
                if (isAdded()) {
                    Pokedex pokedex = response.body();

                    adapter.addAll(pokedex.getPokedexEntries());
                    pokemonListV.setAdapter(adapter);

                    showProgress(false);
                }
            }

            @Override
            public void onFailure(Call<Pokedex> call, Throwable t) {
                Log.e(getTag(), "onFailure() " + t);

                if (isVisible()) {
                    showProgress(false);

                    new MaterialDialog.Builder(getActivity())
                            .title(R.string.alert_title)
                            .content(R.string.alert_server_error)
                            .positiveText(R.string.alert_accept)
                            .onPositive(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                    attemptGetPokemonList();
                                }
                            }).show();
                }
            }
        });
    }

    @OnItemClick(R.id.pokemon_list_listview)
    public void itemClick(int position) {
        if (isVisible()) {
            Bundle args = new Bundle();
            args.putString("resourceUrl", adapter.getItem(position).getSpecie().getUrl());

            MainActivity main = (MainActivity) getActivity();
            main.replaceFragment(new PokemonDetailFragment(), PokemonDetailFragment.class.getSimpleName(), true, args);
        }
    }

    private void showProgress(boolean isVisible) {
        progressV.setVisibility(isVisible ? View.VISIBLE : View.GONE);
    }
}
