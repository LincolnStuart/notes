package co.lincoln.notas.ui.recycler.adapter

import android.content.Context
import android.view.*
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import co.lincoln.notas.R
import co.lincoln.notas.model.Nota
import kotlinx.android.synthetic.main.item_nota.view.*

class ListaDeNotasAdapter(
    private val context: Context,
    private val notas: MutableList<Nota>,
    private val onClickListenerDelegate: (nota: Nota, posicao: Int) -> Unit
) : RecyclerView.Adapter<ListaDeNotasAdapter.NotaViewHolder>() {

    inner class NotaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val campoTitulo = itemView.item_nota_titulo
        private val campoDescricao = itemView.item_nota_descricao

        fun bind(nota: Nota) {
            campoTitulo.text = nota.titulo
            campoDescricao.text = nota.descricao
            itemView.setBackgroundResource(nota.cor.resourceBackgroundId)
            itemView.setOnClickListener {
                onClickListenerDelegate(nota, adapterPosition)
            }
//            itemView.setOnCreateContextMenuListener { menu, _, _ ->
//                menu.setHeaderTitle("Ações")
//                menu.add(Menu.NONE, 1, adapterPosition, "Remover Nota")
//            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotaViewHolder {
        val layout = LayoutInflater.from(context)
            .inflate(R.layout.item_nota, parent, false)
        return NotaViewHolder(layout)
    }

    override fun getItemCount(): Int = notas.size

    override fun onBindViewHolder(holder: NotaViewHolder, position: Int) {
        val nota = notas[position]
        holder.bind(nota)
    }

    fun remove(posicao: Int){
        notas.removeAt(posicao)
        notifyItemRemoved(posicao)
        Toast.makeText(context, "nota removida", Toast.LENGTH_SHORT).show()
    }

    fun adiciona(nota: Nota) {
        notas.add(nota)
        notifyDataSetChanged()
    }

    fun atualiza(nota: Nota, posicao: Int) {
        notas[posicao] = nota
        notifyDataSetChanged()
    }

    fun atualiza(posicaoInicial: Int, posicaoFinal: Int){
        val notaFinal = notas[posicaoFinal]
        notas[posicaoFinal] = notas[posicaoInicial]
        notas[posicaoInicial] = notaFinal
        notifyItemMoved(posicaoInicial, posicaoFinal)
    }

}