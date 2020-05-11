package co.lincoln.notas.model

import java.io.Serializable

class Nota(
    var titulo: String,
    var descricao: String,
    val cor: Cor
) : Serializable