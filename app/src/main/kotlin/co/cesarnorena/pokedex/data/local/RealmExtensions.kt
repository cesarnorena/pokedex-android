package co.cesarnorena.pokedex.data.local

import io.reactivex.Single
import io.realm.*

fun <T : RealmObject> Realm.save(value: T) {
    beginTransaction()
    copyToRealmOrUpdate(value)
    commitTransaction()
}

fun <T : RealmObject> Realm.saveAll(value: List<T>): Single<Unit> {
    return Single.create { emitter ->
        executeTransactionAsync({
            it.copyToRealmOrUpdate(value)
        }, {
            emitter.onSuccess(kotlin.Unit)
        }, {
            emitter.onError(it)
        })
    }
}

inline fun <reified T : RealmObject> Realm.findById(filter: Pair<String, Int>): Single<T> {
    return Single.create { emitter ->
        val query = where(T::class.java).`in`(filter.first, arrayOf(filter.second)).findFirstAsync()

        query.addChangeListener(object : RealmObjectChangeListener<T> {
            override fun onChange(result: T, changeSet: ObjectChangeSet?) {
                emitter.onSuccess(result)
                query.removeChangeListener(this)
            }
        })
    }
}

inline fun <reified T : RealmObject> Realm.findAll(): Single<List<T>> {
    return Single.create { emitter ->
        val query = where(T::class.java).findAllAsync()

        query.addChangeListener(object : RealmChangeListener<RealmResults<T>> {
            override fun onChange(results: RealmResults<T>) {
                emitter.onSuccess(results.toList())
                query.removeChangeListener(this)
            }
        })
    }
}

inline fun <reified T : RealmObject> Realm.removeAll(): Single<Unit> {
    return Single.create { emitter ->
        executeTransactionAsync({
            it.where(T::class.java).findAll().deleteAllFromRealm()
        }, {
            emitter.onSuccess(kotlin.Unit)
        }, {
            emitter.onError(it)
        })
    }
}

