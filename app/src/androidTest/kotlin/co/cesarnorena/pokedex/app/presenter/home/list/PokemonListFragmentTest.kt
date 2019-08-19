package co.cesarnorena.pokedex.app.presenter.home.list

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.contrib.RecyclerViewActions.*
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import co.cesarnorena.pokedex.R
import co.cesarnorena.pokedex.app.presenter.home.HomeActivity
import co.cesarnorena.pokedex.app.presenter.home.list.PokemonListAdapter.ViewHolder
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PokemonListUiTest {

    @get:Rule
    val activityRule = ActivityTestRule(HomeActivity::class.java, false)

    @Test
    fun showPokemonList() {
        onView(withId(R.id.pokemonListView))
            .check(matches(isDisplayed()))

        onView(withId(R.id.pokemonListView))
            .perform(actionOnItemAtPosition<ViewHolder>(0, click()))

        onView(allOf(withId(R.id.pokemonName), isDescendantOfA(withId(R.id.pokemonDetailContainer))))
            .check(matches(withText("Bulbasaur")))
    }
}
