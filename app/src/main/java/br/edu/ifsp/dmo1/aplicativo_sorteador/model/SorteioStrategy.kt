package br.edu.ifsp.dmo1.aplicativo_sorteador.model

import kotlin.random.Random

abstract class SorteioStrategy {

    protected val random = Random.Default

    abstract fun nextNumber(): Int

    abstract fun getLowBorder(): Int

    abstract fun getHighBorder(): Int
}