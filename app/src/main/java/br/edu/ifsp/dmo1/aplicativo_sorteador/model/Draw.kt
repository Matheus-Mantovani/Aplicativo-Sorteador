package br.edu.ifsp.dmo1.aplicativo_sorteador.model

import android.content.Context
import br.edu.ifsp.dmo1.aplicativo_sorteador.R

class Draw(private var border: Int = 0) {

    private lateinit var strategy: SorteioStrategy
    private val history = LinkedHashSet<Int>()

    init {
        if(border == 0) {
            strategy = DefaultLimit
        } else {
            strategy = DefinedLimit(border)
        }
    }

    fun getNumber(): Int {
        if(history.size < strategy.getHighBorder()) {
            var number: Int
            do {
                number = strategy.nextNumber()
            } while(!history.add(number))

            return number
        }
        return -1
    }

    fun getHistory(context: Context) = history.mapIndexed { index, number -> "${index+1}º " + String.format(context.getString(R.string.sorteado)) + " = $number" }

    fun getLowBorder() = strategy.getLowBorder()

    fun getHighBorder() = strategy.getHighBorder()
}