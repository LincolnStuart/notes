package co.lincoln.notas.ui.strategy.formulario

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import co.lincoln.notas.model.Cor
import co.lincoln.notas.model.Nota
import co.lincoln.notas.util.CHAVE_NOTA
import co.lincoln.notas.util.TITULO_FORMULARIO_ADICAO_NOTAS
import kotlinx.android.synthetic.main.activity_formulario_nota.view.*

class FormularioAdicaoStrategy(
    private val activity: AppCompatActivity,
    private val cor: Cor
) : FormularioStrategy {
    private val viewDaActivity = activity.window.decorView

    override val nota: Nota
        get() {
            val titulo = viewDaActivity.formulario_nota_titulo.text.toString()
            val descricao = viewDaActivity.formulario_nota_descricao.text.toString()
            val nota = Nota(titulo, descricao, cor)
            return nota
        }

    override fun configuraFormulario() {
        activity.title = TITULO_FORMULARIO_ADICAO_NOTAS
        viewDaActivity.setBackgroundResource(cor.resourceBackgroundId)
    }

    override fun configuraRetornoDeNota() {
        val retorno = Intent()
        retorno.putExtra(CHAVE_NOTA, nota)
        activity.setResult(Activity.RESULT_OK, retorno)
    }
}