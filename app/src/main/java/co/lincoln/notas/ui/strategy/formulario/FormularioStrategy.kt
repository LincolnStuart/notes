package co.lincoln.notas.ui.strategy.formulario

import co.lincoln.notas.model.Nota

interface FormularioStrategy {
    val nota: Nota

    fun configuraFormulario()
    fun configuraRetornoDeNota()
}