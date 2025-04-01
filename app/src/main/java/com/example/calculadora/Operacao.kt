package com.example.calculadora

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class Operacao (
    output: String
) {
    var output by mutableStateOf(output)
    var primeiroValor = ""
    var segundoValor = ""
    var tipoOperacao: String = ""

    fun addOperacao(tipo: String) {
        tipoOperacao = tipo
        if (primeiroValor.isEmpty()) {
            primeiroValor = output
        }
        output = "0"
    }

    fun addNumero(numero: String) {
        if (output.equals("0")) output = numero else output += numero
    }

    fun calculaResultado() {
        segundoValor = output
        if (tipoOperacao.equals("+")) {
            output = (primeiroValor.toInt() + segundoValor.toInt()).toString()
        }
        if (tipoOperacao.equals("-")) {
            output = (primeiroValor.toInt() - segundoValor.toInt()).toString()
        }
        if (tipoOperacao.equals("*")) {
            output = (primeiroValor.toInt() * segundoValor.toInt()).toString()
        }
        if (tipoOperacao.equals("/")) {
            output = (primeiroValor.toInt() / segundoValor.toInt()).toString()
        }

        tipoOperacao = ""
        segundoValor = ""
        primeiroValor = ""
    }

    fun limpaOutput() {
        output = "0"
        tipoOperacao = ""
    }
}