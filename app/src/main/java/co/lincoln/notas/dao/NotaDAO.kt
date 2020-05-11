package co.lincoln.notas.dao

import co.lincoln.notas.model.Nota
import co.lincoln.notas.extensions.MutableListExtension

class NotaDAO {

    val notas: List<Nota> = Companion.notas

    companion object{
        private val notas: MutableList<Nota> = mutableListOf()
    }

    fun adiciona(nota: Nota) {
        Companion.notas.add(nota)
    }

    fun remove(position: Int){
        Companion.notas.removeAt(position)
    }

    fun altera(nota: Nota, position: Int){
        Companion.notas[position] = nota
    }

    fun alteraPosicoes(posicaoInicial: Int, posicaoFinal: Int){
        val notaFinal = Companion.notas[posicaoFinal]
        Companion.notas[posicaoFinal] = Companion.notas[posicaoInicial]
        Companion.notas[posicaoInicial] = notaFinal
    }


}