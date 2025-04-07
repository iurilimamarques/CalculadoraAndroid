package com.example.calculadora

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun NumeroButton(acao: () -> Unit, numero: String, isZero: Boolean = false) {
    Button(onClick = acao,
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF333333)),
        modifier =
            Modifier
                .padding(10.dp)
                .width(if (isZero) 180.dp else 77.dp)
                .size(77.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = if (isZero) Arrangement.Start else Arrangement.Center
        ) {
            Text(text = numero, fontSize = 40.sp)
        }
    }
}

@Composable
fun AcaoButton(acao: () -> Unit, operador: String, greyButton: Boolean = false) {
    var color = if (greyButton) Color(0xFFA5A5A5) else Color(0xFFFF9F0A)
    Button(onClick = acao,
        colors = ButtonDefaults.buttonColors(containerColor = color),
        modifier =
            Modifier
                .padding(10.dp)
                .size(77.dp)) {
        Text(text = operador, fontSize = 40.sp)
    }
}

@Composable
fun Calculadora(operacao: Operacao) {
    Column (modifier = Modifier.fillMaxSize().background(Color(0xFF000000)),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "${operacao.output}",
            color = Color.White,
            fontSize = 50.sp,
            modifier = Modifier.fillMaxWidth().padding(0.dp, 0.dp, 45.dp, 0.dp),
            textAlign = TextAlign.Right)

        Row (modifier = Modifier.background(Color(0xFF000000)).fillMaxWidth(),
            horizontalArrangement = Arrangement.Center) {
            AcaoButton(acao = {operacao.limpaOutput()}, operador = "AC", true)
            AcaoButton(acao = {operacao.limpaOutput()}, operador = "+/-", true)
            AcaoButton(acao = {operacao.limpaOutput()}, operador = "%", true)
            AcaoButton(acao = {operacao.addOperacao("/")}, operador = "/", false)
        }

        Row (modifier = Modifier.background(Color(0xFF000000)).fillMaxWidth(),
            horizontalArrangement = Arrangement.Center) {
            NumeroButton(acao = {operacao.addNumero("7")}, numero = "7")
            NumeroButton(acao = {operacao.addNumero("8")}, numero = "8")
            NumeroButton(acao = {operacao.addNumero("9")}, numero = "9")
            AcaoButton(acao = {operacao.addOperacao("*")}, operador = "x")
        }

        Row (modifier = Modifier.background(Color(0xFF000000)).fillMaxWidth(),
            horizontalArrangement = Arrangement.Center) {
            NumeroButton(acao = {operacao.addNumero("4")}, numero = "4")
            NumeroButton(acao = {operacao.addNumero("5")}, numero = "5")
            NumeroButton(acao = {operacao.addNumero("6")}, numero = "6")
            AcaoButton(acao = {operacao.addOperacao("-")}, operador = "-")
        }

        Row (modifier = Modifier.background(Color(0xFF000000)).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly) {
            NumeroButton(acao = {operacao.addNumero("1")}, numero = "1")
            NumeroButton(acao = {operacao.addNumero("2")}, numero = "2")
            NumeroButton(acao = {operacao.addNumero("3")}, numero = "3")
            AcaoButton(acao = {operacao.addOperacao("+")}, operador = "+")
        }

        Row (modifier = Modifier.background(Color(0xFF000000)).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly) {
            NumeroButton(acao = {operacao.addNumero("0")}, numero = "0", isZero = true)
            NumeroButton(acao = {operacao.addNumero(",")}, numero = ",")
            AcaoButton(acao = {operacao.calculaResultado()}, operador = "=")
        }
    }
}

@Preview()
@Composable
fun CalculadoraView() {
    val operacao = Operacao("0")
    Calculadora(operacao)
}
