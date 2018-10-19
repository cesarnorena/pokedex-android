package co.cesarnorena.pokedex.app.extensions

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity

fun AppCompatActivity.addFragment(
    fragment: Fragment,
    resource: Int,
    addToBackStack: Boolean = false
) {
    with(supportFragmentManager.beginTransaction()) {
        slideAnimation()
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
        slideAnimation()
        replace(resource, fragment)
        commit()
    }
}

fun FragmentTransaction.slideAnimation() {
    setCustomAnimations(
        android.R.anim.slide_in_left,
        android.R.anim.slide_out_right,
        android.R.anim.slide_in_left,
        android.R.anim.slide_out_right
    )
}
