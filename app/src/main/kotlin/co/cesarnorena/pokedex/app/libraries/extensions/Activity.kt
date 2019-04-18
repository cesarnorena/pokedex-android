package co.cesarnorena.pokedex.app.libraries.extensions

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity

fun AppCompatActivity.addFragment(
    fragment: Fragment,
    resource: Int,
    addToBackStack: Boolean = false
) {
    with(supportFragmentManager.beginTransaction()) {
        showAnimated()
        add(resource, fragment)
        if (addToBackStack) addToBackStack(fragment.tag)
        commit()
    }
}

fun AppCompatActivity.replaceFragment(
    fragment: Fragment,
    resource: Int
) {
    with(supportFragmentManager.beginTransaction()) {
        showAnimated()
        replace(resource, fragment)
        commit()
    }
}

fun FragmentTransaction.showAnimated() {
    setCustomAnimations(
        android.R.anim.fade_in,
        android.R.anim.fade_out
    )
}
