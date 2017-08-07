package co.cesarnorena.pokedex.app.utils.extensions

import android.app.Fragment
import android.app.FragmentManager
import android.support.v7.app.AppCompatActivity

private enum class FragmentOperation {
    ADD, REPLACE, REMOVE
}

fun AppCompatActivity.addFragment(fragment: Fragment, resource: Int) =
        fragmentTransaction(FragmentOperation.ADD, fragment, fragmentManager, resource)

fun AppCompatActivity.replaceFragment(fragment: Fragment, resource: Int) =
        fragmentTransaction(FragmentOperation.REPLACE, fragment, fragmentManager, resource)

fun AppCompatActivity.removeFragment(fragment: Fragment) =
        fragmentTransaction(FragmentOperation.REMOVE, fragment, fragmentManager)

fun AppCompatActivity.getLastFragment(resource: Int): Fragment? =
        fragmentManager.findFragmentById(resource)

private fun fragmentTransaction(operation: FragmentOperation, fragment: Fragment,
                                fragmentManager: FragmentManager, resource: Int = 0) {
    if (operation != FragmentOperation.REMOVE && resource == 0)
        throw IllegalArgumentException("Resource identifier required")
    val transaction = fragmentManager.beginTransaction()
    when (operation) {
        FragmentOperation.ADD -> transaction.add(resource, fragment)
        FragmentOperation.REPLACE -> transaction.replace(resource, fragment)
        FragmentOperation.REMOVE -> transaction.remove(fragment)
    }
    transaction.commit()
}

