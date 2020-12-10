package com.cesarnorena.pokedex.app.presenter.home

import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.cesarnorena.pokedex.R
import com.cesarnorena.pokedex.app.PokedexApplication
import com.cesarnorena.pokedex.app.libraries.injection.DaggerPokedexComponentTest
import com.cesarnorena.pokedex.app.presenter.home.list.PokemonListAdapter.ViewHolder
import com.cesarnorena.pokedex.data.model.PokedexEntry
import com.cesarnorena.pokedex.data.model.Pokemon
import com.cesarnorena.pokedex.data.model.Specie
import com.cesarnorena.pokedex.data.model.Type
import com.cesarnorena.pokedex.data.model.TypeSlot
import com.cesarnorena.pokedex.domain.repository.LocalRepository
import com.cesarnorena.pokedex.domain.repository.RemoteRepository
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@RunWith(AndroidJUnit4::class)
class HomeActivityUiTest {

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
        val typeSlots = listOf(TypeSlot(0, Type("grass")), TypeSlot(1, Type("poison")))
        val pokemon = Pokemon(1, "bulbasaur", typeSlots)
        val pokedex = listOf(PokedexEntry(pokemon.id, Specie(pokemon.name)))

        whenever(localRepository.getPokedex())
            .thenReturn(Single.just(pokedex))

        whenever(remoteRepository.getPokemon(pokemon.id))
            .thenReturn(Single.just(pokemon))

        val scenario = launchActivity()

        onView(withId(R.id.pokemonListView))
            .check(matches(isDisplayed()))

        onView(withId(R.id.pokemonListView))
            .perform(actionOnItemAtPosition<ViewHolder>(0, click()))

        onView(allOf(
            withId(R.id.pokemonName),
            isDescendantOfA(withId(R.id.pokemonDetailContainer))
        )).check(matches(withText(pokemon.name.capitalize())))

        scenario.close()
    }

    @Test
    fun showInternetError() {
        whenever(localRepository.getPokedex())
            .thenReturn(Single.error(Exception()))

        val scenario = launchActivity()

        onView(withId(R.id.pokemonListView))
            .check(matches(not(isDisplayed())))

        onView(withId(R.id.internetError))
            .check(matches(isDisplayed()))

        scenario.close()
    }

    private fun launchActivity() = ActivityScenario.launch(HomeActivity::class.java)
}
