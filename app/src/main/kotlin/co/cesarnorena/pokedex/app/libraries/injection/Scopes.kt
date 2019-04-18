package co.cesarnorena.pokedex.app.libraries.injection

import javax.inject.Scope

@Scope
@Retention(value = AnnotationRetention.RUNTIME)
annotation class ActivityScoped

@Scope
@Retention(value = AnnotationRetention.RUNTIME)
annotation class FragmentScoped
