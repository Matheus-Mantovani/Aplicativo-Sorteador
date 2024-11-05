package br.edu.ifsp.dmo1.aplicativo_sorteador.model

class Draw(private val border: Int = 0) {

    private lateinit var strategy: SorteioStrategy
    private val history = HashSet<Int>()

    init {
        if(border == 0)
            strategy = DefaultLimit
        else
            strategy = DefinedLimit(border)
    }

    fun getNumber(): Int {
        var number: Int
        do {
            number = strategy.nextNumber()
        } while(!history.add(number))

        return number
    }

    fun getHistory() = ArrayList(history)

    fun getLowBorder() = strategy.getLowBorder()

    fun getHighBorder() = strategy.getHighBorder()
}