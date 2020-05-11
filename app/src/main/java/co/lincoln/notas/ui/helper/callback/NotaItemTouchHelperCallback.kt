package co.lincoln.notas.ui.helper.callback

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import co.lincoln.notas.dao.NotaDAO
import co.lincoln.notas.ui.recycler.adapter.ListaDeNotasAdapter

class NotaItemTouchHelperCallback(
    val context: Context,
    val adapter: ListaDeNotasAdapter
) : ItemTouchHelper.Callback() {

    private val dao = NotaDAO()

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val moveFlags = (ItemTouchHelper.RIGHT
                or ItemTouchHelper.LEFT
                or ItemTouchHelper.UP
                or ItemTouchHelper.DOWN)
        val swipeFlags = ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        return makeMovementFlags(moveFlags, swipeFlags)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        dao.alteraPosicoes(viewHolder.adapterPosition, target.adapterPosition)
        adapter.atualiza(viewHolder.adapterPosition, target.adapterPosition)
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        AlertDialog.Builder(context)
            .setTitle("Confirmação")
            .setMessage("Deseja excluir esta nota?")
            .setPositiveButton("Sim") { _, _ ->
                dao.remove(viewHolder.adapterPosition)
                adapter.remove(viewHolder.adapterPosition)
            }
            .setNegativeButton("Não") { _, _ ->
                adapter.notifyItemChanged(viewHolder.adapterPosition)
            }
            .setOnCancelListener {
                adapter.notifyItemChanged(viewHolder.adapterPosition)
            }
            .create()
            .show()
    }
}