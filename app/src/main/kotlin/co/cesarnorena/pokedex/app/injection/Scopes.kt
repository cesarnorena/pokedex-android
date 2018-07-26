package co.cesarnorena.pokedex.app.injection

import javax.inject.Scope

@Scope
@Retention(value = AnnotationRetention.RUNTIME)
annotation class ActivityScoped

@Scope
@Retention(value = AnnotationRetention.RUNTIME)
annotation class FragmentScoped
