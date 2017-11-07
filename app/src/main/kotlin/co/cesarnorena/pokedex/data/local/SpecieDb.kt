package co.cesarnorena.pokedex.data.local

import io.realm.RealmObject

open class SpecieDb() : RealmObject() {

    lateinit var name: String
    lateinit var url: String

    constructor(name: String, url: String) : this() {
        this.name = name
        this.url = url
    }
}
