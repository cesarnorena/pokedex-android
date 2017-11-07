package co.cesarnorena.pokedex.data.local

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class PokemonDb() : RealmObject() {

    @PrimaryKey
    var id: Int = 0
    lateinit var name: String

    constructor(id: Int, name: String) : this() {
        this.id = id
        this.name = name
    }

}
