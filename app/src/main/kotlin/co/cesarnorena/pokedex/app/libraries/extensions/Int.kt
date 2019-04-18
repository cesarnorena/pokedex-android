package co.cesarnorena.pokedex.app.libraries.extensions

fun Int.formattedId(): String {
    return when {
        this < 10 -> "00$this"
        this < 100 -> "0$this"
        else -> this.toString()
    }
}
