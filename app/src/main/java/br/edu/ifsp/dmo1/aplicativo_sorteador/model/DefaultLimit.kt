package br.edu.ifsp.dmo1.aplicativo_sorteador.model

object DefaultLimit: SorteioStrategy() {
    private val BORDER_LIMIT = 1000


    override fun nextNumber(): Int {
        return random.nextInt(1, BORDER_LIMIT+1)
    }

    override fun getLowBorder(): Int {
        return 1
    }

    override fun getHighBorder(): Int {
        return BORDER_LIMIT
    }
}