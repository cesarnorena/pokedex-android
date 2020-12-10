package com.cesarnorena.pokedex.app.libraries.extensions

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction

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
