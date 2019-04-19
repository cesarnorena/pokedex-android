package co.cesarnorena.pokedex.app.libraries.reactivex

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

fun Disposable.addDisposeBag(compositeDisposable: CompositeDisposable): Disposable {
    compositeDisposable.add(this)
    return this
}
