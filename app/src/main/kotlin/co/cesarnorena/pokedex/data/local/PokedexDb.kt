package co.cesarnorena.pokedex.data.local

import io.realm.RealmObject

open class PokedexDb() : RealmObject() {

    var number: Int = 0
    var specie: SpecieDb? = null

    constructor(number: Int, specie: SpecieDb) : this() {
        this.number = number
        this.specie = specie
    }

}
