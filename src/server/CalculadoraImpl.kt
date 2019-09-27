package server

import util.Matematica.CalculadoraPOA


class CalculadoraImpl: CalculadoraPOA() {
    override fun soma(arg1: Float, arg2: Float): Float {
        return arg1+arg2
    }

    override fun divisao(arg1: Float, arg2: Float): Float {
        return arg1/arg2
    }
}