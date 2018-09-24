package co.cesarnorena.pokedex.app.utils.extensions

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity

fun AppCompatActivity.addFragment(
    fragment: Fragment,
    resource: Int,
    addToBackStack: Boolean = false
) {
    fragmentTransaction(fragment, supportFragmentManager, resource, addToBackStack)
}

private fun fragmentTransaction(
    fragment: Fragment,
    fragmentManager: FragmentManager,
    resource: Int = 0,
    addToBackStack: Boolean = false
) {
    with(fragmentManager.beginTransaction()) {
        add(resource, fragment)
        if (addToBackStack) addToBackStack(fragment.tag)
        commit()
    }
}
