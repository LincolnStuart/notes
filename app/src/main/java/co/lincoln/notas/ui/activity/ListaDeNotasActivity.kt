package co.lincoln.notas.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import co.lincoln.notas.R
import co.lincoln.notas.dao.NotaDAO
import co.lincoln.notas.model.Nota
import co.lincoln.notas.ui.dialog.EscolhaDeCorDialog
import co.lincoln.notas.ui.helper.callback.NotaItemTouchHelperCallback
import co.lincoln.notas.ui.recycler.adapter.ListaDeNotasAdapter
import co.lincoln.notas.util.*
import kotlinx.android.synthetic.main.activity_lista_de_notas.*

class ListaDeNotasActivity : AppCompatActivity() {

    private val viewDaActivity by lazy {
        window.decorView
    }
    private val viewGroupDaActivity by lazy {
        viewDaActivity as ViewGroup
    }
    private val dao = NotaDAO()
    private val notas: MutableList<Nota> = mutableListOf()
    private val adapter = ListaDeNotasAdapter(this, notas)
    { nota, posicao ->
        configuraEventoDeCliqueEdicao(nota, posicao)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_de_notas)
        title = TITULO_LISTA_DE_NOTAS
        configuraComponentes()
    }

    private fun configuraEventoDeCliqueEdicao(nota: Nota, posicao: Int) {
        val intent = Intent(this, FormularioNotaActivity::class.java)
        intent.putExtra(CHAVE_NOTA, nota)
        intent.putExtra(CHAVE_POSICAO, posicao)
        startActivityForResult(intent, CODIGO_REQUISICAO_EDICAO)
    }

    private fun configuraComponentes() {
        configuraRecyclerView()
        configuraBotaoAdicionaNota()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        data?.let {
            if (ehUmRetornoDeAdicao(requestCode, it))
                trataAdicao(resultCode, data)
            if (ehUmRetornoDeEdicao(requestCode, it))
                trataEdicao(resultCode, data)
        }
    }

    private fun trataAdicao(resultCode: Int, data: Intent) {
        if (resultCode == Activity.RESULT_OK) {
            val nota = data.getSerializableExtra(CHAVE_NOTA) as Nota
            adicionaNota(nota)
        }
    }

    private fun trataEdicao(resultCode: Int, data: Intent) {
        if (resultCode == Activity.RESULT_OK) {
            val nota = data.getSerializableExtra(CHAVE_NOTA) as Nota
            val posicao = data.getIntExtra(CHAVE_POSICAO, POSICAO_INVALIDA)
            alteraNota(posicao, nota)
        }
    }

    private fun alteraNota(posicao: Int, nota: Nota) {
        if (posicao != POSICAO_INVALIDA) {
            dao.altera(nota, posicao)
            adapter.atualiza(nota, posicao)
        }
    }

    private fun ehUmRetornoDeEdicao(
        requestCode: Int,
        it: Intent
    ) = requestCode == CODIGO_REQUISICAO_EDICAO && it.hasExtra(CHAVE_NOTA)

    private fun ehUmRetornoDeAdicao(
        requestCode: Int,
        it: Intent
    ) = requestCode == CODIGO_REQUISICAO_ADICAO && it.hasExtra(CHAVE_NOTA)

    private fun adicionaNota(nota: Nota) {
        dao.adiciona(nota)
        atualizaAdapter(nota)
    }

    private fun atualizaAdapter(nota: Nota) {
        adapter.adiciona(nota)
    }

    private fun configuraBotaoAdicionaNota() {
        lista_de_notas_botao_adiciona.setOnClickListener {
            EscolhaDeCorDialog(this, viewGroupDaActivity).exibe()
        }
    }

    private fun configuraRecyclerView() {
        notas.addAll(dao.notas)
        lista_de_notas_listview.adapter = adapter
        ItemTouchHelper(NotaItemTouchHelperCallback(this, adapter))
            .attachToRecyclerView(lista_de_notas_listview)
    }

//    override fun onContextItemSelected(item: MenuItem?): Boolean {
//        if(item?.itemId == 1){
//            Toast.makeText(this, "posição : ${item.order}", Toast.LENGTH_LONG).show()
//        }
//        return super.onContextItemSelected(item)
//    }
}
