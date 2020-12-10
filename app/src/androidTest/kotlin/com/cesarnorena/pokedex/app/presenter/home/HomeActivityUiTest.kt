package com.cesarnorena.pokedex.app.presenter.home

import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.cesarnorena.pokedex.R
import com.cesarnorena.pokedex.app.PokedexApplication
import com.cesarnorena.pokedex.app.libraries.injection.DaggerPokedexComponentTest
import com.cesarnorena.pokedex.app.presenter.home.list.PokemonListAdapter.ViewHolder
import com.cesarnorena.pokedex.data.model.*
import com.cesarnorena.pokedex.domain.repository.LocalRepository
import com.cesarnorena.pokedex.domain.repository.RemoteRepository
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@RunWith(AndroidJUnit4::class)
class HomeActivityUiTest {

    @get:Rule
    val activityRule = ActivityTestRule(HomeActivity::class.java, false, false)

    @Inject
    lateinit var localRepository: LocalRepository

    @Inject
    lateinit var remoteRepository: RemoteRepository

    @Before
    fun before() {
        val app: PokedexApplication = ApplicationProvider.getApplicationContext()

        val testInjector = DaggerPokedexComponentTest.builder()
            .application(app)
            .build()

        testInjector.inject(app)
        testInjector.inject(this)
    }

    @Test
    fun showPokemonDetailsOfFirstItem() {
        val typeList = listOf(TypeSlot(0, Type("grass")), TypeSlot(1, Type("poison")))
        val pokemon = Pokemon(1, "bulbasaur", typeList)
        val pokedex = listOf(PokedexEntry(pokemon.id, Specie(pokemon.name)))

        whenever(localRepository.getPokedex())
            .thenReturn(Single.just(pokedex))

        whenever(remoteRepository.getPokemon(pokemon.id))
            .thenReturn(Single.just(pokemon))

        launchActivity()

        onView(withId(R.id.pokemonListView))
            .check(matches(isDisplayed()))

        onView(withId(R.id.pokemonListView))
            .perform(actionOnItemAtPosition<ViewHolder>(0, click()))

        onView(allOf(
            withId(R.id.pokemonName),
            isDescendantOfA(withId(R.id.pokemonDetailContainer))
        )).check(matches(withText(pokemon.name.capitalize())))
    }

    @Test
    fun showInternetError() {
        whenever(localRepository.getPokedex())
            .thenReturn(Single.error(Exception()))

        launchActivity()

        onView(withId(R.id.pokemonListView))
            .check(matches(not(isDisplayed())))

        onView(withId(R.id.internetError))
            .check(matches(isDisplayed()))
    }

    private fun launchActivity() = activityRule.launchActivity(Intent())
}
