package co.cesarnorena.pokedex.app.utils.extensions

import android.app.Fragment
import android.app.FragmentManager
import android.support.v7.app.AppCompatActivity

private enum class FragmentOperation {
    ADD, REPLACE, REMOVE
}

fun AppCompatActivity.addFragment(fragment: Fragment, resource: Int,
                                  addToBackStack: Boolean = false) {
    fragmentTransaction(FragmentOperation.ADD, fragment, fragmentManager, resource, addToBackStack)
}

fun AppCompatActivity.replaceFragment(fragment: Fragment, resource: Int,
                                      addToBackStack: Boolean = false) {
    fragmentTransaction(FragmentOperation.REPLACE, fragment, fragmentManager,
            resource, addToBackStack)
}

fun AppCompatActivity.removeFragment(fragment: Fragment) {
    fragmentTransaction(FragmentOperation.REMOVE, fragment, fragmentManager)
}

fun AppCompatActivity.getLastFragment(resource: Int): Fragment? {
    return fragmentManager.findFragmentById(resource)
}

private fun fragmentTransaction(operation: FragmentOperation, fragment: Fragment,
                                fragmentManager: FragmentManager, resource: Int = 0,
                                addToBackStack: Boolean = false) {
    if (operation != FragmentOperation.REMOVE && resource == 0)
        throw IllegalArgumentException("Resource identifier required")
    val transaction = fragmentManager.beginTransaction()
    when (operation) {
        FragmentOperation.ADD -> transaction.add(resource, fragment)
        FragmentOperation.REPLACE -> transaction.replace(resource, fragment)
        FragmentOperation.REMOVE -> transaction.remove(fragment)
    }
    if (addToBackStack) transaction.addToBackStack(fragment.tag)
    transaction.commit()
}

