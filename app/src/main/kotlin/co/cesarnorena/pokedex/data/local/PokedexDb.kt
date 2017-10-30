package co.cesarnorena.pokedex.data.local

import co.cesarnorena.pokedex.data.model.Specie
import io.realm.RealmObject

class PokedexDb() : RealmObject() {

    var number: Int = 0
    lateinit var specie: Specie

    constructor(number: Int, specie: Specie) : this() {
        this.number = number
        this.specie = specie
    }

}
