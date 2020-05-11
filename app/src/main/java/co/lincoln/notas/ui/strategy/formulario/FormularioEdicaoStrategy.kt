package co.lincoln.notas.ui.strategy.formulario

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import co.lincoln.notas.model.Nota
import co.lincoln.notas.util.CHAVE_NOTA
import co.lincoln.notas.util.CHAVE_POSICAO
import co.lincoln.notas.util.TITULO_FORMULARIO_EDICAO_NOTAS
import kotlinx.android.synthetic.main.activity_formulario_nota.view.*

class FormularioEdicaoStrategy(
    private val activity: AppCompatActivity,
    override val nota: Nota,
    val posicao: Int
) : FormularioStrategy {
    private val viewDaActivity = activity.window.decorView

    override fun configuraFormulario() {
        activity.title = TITULO_FORMULARIO_EDICAO_NOTAS
        viewDaActivity.formulario_nota_titulo.setText(nota.titulo)
        viewDaActivity.formulario_nota_descricao.setText(nota.descricao)
        viewDaActivity.setBackgroundResource(nota.cor.resourceBackgroundId)
    }

    override fun configuraRetornoDeNota() {
        nota.titulo = viewDaActivity.formulario_nota_titulo.text.toString()
        nota.descricao = viewDaActivity.formulario_nota_descricao.text.toString()
        val retorno = Intent()
        retorno.putExtra(CHAVE_NOTA, nota)
        retorno.putExtra(CHAVE_POSICAO, posicao)
        activity.setResult(Activity.RESULT_OK, retorno)
    }
}