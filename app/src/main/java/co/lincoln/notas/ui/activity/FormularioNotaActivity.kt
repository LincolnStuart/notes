package co.lincoln.notas.ui.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import co.lincoln.notas.R
import co.lincoln.notas.model.Cor
import co.lincoln.notas.model.Nota
import co.lincoln.notas.ui.strategy.formulario.FormularioAdicaoStrategy
import co.lincoln.notas.ui.strategy.formulario.FormularioEdicaoStrategy
import co.lincoln.notas.ui.strategy.formulario.FormularioStrategy
import co.lincoln.notas.util.CHAVE_COR
import co.lincoln.notas.util.CHAVE_NOTA
import co.lincoln.notas.util.CHAVE_POSICAO
import co.lincoln.notas.util.POSICAO_INVALIDA

class FormularioNotaActivity : AppCompatActivity() {

    private val strategy: FormularioStrategy by lazy {
        defineEstrategia()
    }

    private fun defineEstrategia(): FormularioStrategy {
        if (intent.hasExtra(CHAVE_NOTA) && intent.hasExtra(CHAVE_POSICAO)) {
            val nota = recuperaNota()
            val posicao = intent.getIntExtra(CHAVE_POSICAO, POSICAO_INVALIDA)
            return FormularioEdicaoStrategy(this, nota, posicao)
        }
        val cor = recuperaCor()
        return FormularioAdicaoStrategy(this, cor)
    }

    private fun recuperaCor(): Cor {
        if (intent.hasExtra(CHAVE_COR)) {
            return intent.getSerializableExtra(CHAVE_COR) as Cor
        }
        return Cor.AMARELA
    }

    private fun recuperaNota() = intent.getSerializableExtra(CHAVE_NOTA) as Nota

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario_nota)
        configuraLayout()
    }

    private fun configuraLayout() {
        strategy.configuraFormulario()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_formulario_nota, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        item?.let {
            if (ehMenuSalvaNota(it)) {
                strategy.configuraRetornoDeNota()
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun ehMenuSalvaNota(menuItem: MenuItem) =
        menuItem.itemId == R.id.menu_formulario_nota_salva


}
