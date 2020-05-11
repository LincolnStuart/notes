package co.lincoln.notas.model

import co.lincoln.notas.R
import java.io.Serializable

enum class Cor (val resourceBackgroundId: Int, val descricao: String) : Serializable{
    AMARELA(R.drawable.bg_nota_amarela, "Amarela"),
    VERMELHA(R.drawable.bg_nota_vermelha, "Vermelha"),
    VERDE(R.drawable.bg_nota_verde, "Verde")
}