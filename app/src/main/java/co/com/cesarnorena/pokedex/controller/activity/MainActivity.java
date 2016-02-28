package co.com.cesarnorena.pokedex.controller.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import co.com.cesarnorena.pokedex.R;
import co.com.cesarnorena.pokedex.controller.fragment.PokemonListFragment;

/**
 * Created by Cesar Norena on 16/01/2016.
 */
public class MainActivity extends AppCompatActivity {

    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFragment();
    }

    private void initFragment() {
        fragment = new PokemonListFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragment_main_container, fragment,
                PokemonListFragment.class.getSimpleName());
        transaction.commit();
    }

    public void replaceFragment(Fragment newFragment, String newTag, boolean addToBackStack) {
        String tag = fragment.getTag();
        fragment = newFragment;

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_main_container, newFragment, newTag);
        if (addToBackStack)
            transaction.addToBackStack(tag);
        transaction.commit();
    }

    public void replaceFragment(Fragment newFragment, String newTag, boolean addToBackStack, Bundle args) {
        newFragment.setArguments(args);
        replaceFragment(newFragment, newTag, addToBackStack);
    }

    @Override
    public void onBackPressed() {
        FragmentManager manager = getSupportFragmentManager();
        int backStackCount = manager.getBackStackEntryCount();

        if (backStackCount > 0) {
            FragmentManager.BackStackEntry backStackEntry = manager.getBackStackEntryAt(backStackCount - 1);

            int id = backStackEntry.getId();
            fragment = manager.findFragmentByTag(backStackEntry.getName());
            manager.popBackStack(id, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        } else
            super.onBackPressed();
    }
}
