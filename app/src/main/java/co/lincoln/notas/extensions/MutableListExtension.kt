package co.lincoln.notas.extensions

import co.lincoln.notas.model.Nota

class MutableListExtension {

    fun MutableList<Nota>.swap(initialPosition: Int, finalPosition: Int) {
        val finalObject = this[finalPosition]
        this[finalPosition] = this[initialPosition]
        this[initialPosition] = finalObject
    }

}