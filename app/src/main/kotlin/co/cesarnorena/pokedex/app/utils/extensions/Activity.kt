package co.cesarnorena.pokedex.app.utils.extensions

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity

fun AppCompatActivity.addFragment(
    fragment: Fragment,
    resource: Int,
    addToBackStack: Boolean = false
) {
    with(supportFragmentManager.beginTransaction()) {
        add(resource, fragment)
        if (addToBackStack) addToBackStack(fragment.tag)
        commit()
    }
}
