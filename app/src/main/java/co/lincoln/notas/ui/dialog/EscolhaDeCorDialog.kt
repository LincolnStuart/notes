package co.lincoln.notas.ui.dialog

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import co.lincoln.notas.R
import co.lincoln.notas.model.Cor
import co.lincoln.notas.ui.activity.FormularioNotaActivity
import co.lincoln.notas.util.CHAVE_COR
import co.lincoln.notas.util.CODIGO_REQUISICAO_ADICAO
import kotlinx.android.synthetic.main.fragment_escolha_cor_nota.view.*

class EscolhaDeCorDialog(
    private val context: Context,
    private val parent: ViewGroup
) {
    private val activity = context as AppCompatActivity
    private val dialog = AlertDialog.Builder(context).setView(criaView()).create()

    private fun criaView(): View {
        val layout = LayoutInflater.from(context)
            .inflate(R.layout.fragment_escolha_cor_nota, parent, false)
        configuraBotoes(layout)
        return layout
    }

    private fun configuraBotoes(view: View) {
        val intent = Intent(context, FormularioNotaActivity::class.java)
        configuraBotao(view.escolha_cor_nota_opcao_amarela, intent, Cor.AMARELA)
        configuraBotao(view.escolha_cor_nota_opcao_vermelha, intent, Cor.VERMELHA)
        configuraBotao(view.escolha_cor_nota_opcao_verde, intent, Cor.VERDE)
    }

    private fun configuraBotao(view: ImageView, intent: Intent, cor: Cor) {
        view.setOnClickListener {
            intent.putExtra(CHAVE_COR, cor)
            activity.startActivityForResult(intent, CODIGO_REQUISICAO_ADICAO)
            dialog.dismiss()
        }
    }

    fun exibe() {
        dialog.show()
    }

}