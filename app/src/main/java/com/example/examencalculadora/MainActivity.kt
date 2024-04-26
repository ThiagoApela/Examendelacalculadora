package com.example.examencalculadora

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.examencalculadora.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var currentOperation: String? = null
    private var operand1: Double? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.apply {
            button0.setOnClickListener { numeroPulsado("0") }
            button1.setOnClickListener { numeroPulsado("1") }
            button2.setOnClickListener { numeroPulsado("2") }
            button3.setOnClickListener { numeroPulsado("3") }
            button4.setOnClickListener { numeroPulsado("4") }
            button5.setOnClickListener { numeroPulsado("5") }
            button6.setOnClickListener { numeroPulsado("6") }
            button7.setOnClickListener { numeroPulsado("7") }
            button8.setOnClickListener { numeroPulsado("8") }
            button9.setOnClickListener { numeroPulsado("9") }
            buttonPunto.setOnClickListener { numeroPulsado(".") }

            buttonSuma.setOnClickListener { operacionPulsada('+') }
            buttonResta.setOnClickListener { operacionPulsada('-') }
            buttonMultiplicar.setOnClickListener { operacionPulsada('*') }
            buttondividir.setOnClickListener { operacionPulsada('/') }
            buttonBorrar.setOnClickListener { borrar() }
            buttonLimpiar.setOnClickListener { clear() }
            buttonSalir.setOnClickListener { finish() }
            buttonCalcular.setOnClickListener { calcularResultado() }
        }
    }
    private fun numeroPulsado(digit: String) {
        binding.txtcaja.append(digit)
    }
    private fun operacionPulsada(operator: Char) {
        currentOperation = operator.toString()
        operand1 = binding.txtcaja.text.toString().toDoubleOrNull()
        binding.txtcaja.text.clear()
    }
    private fun calcularResultado() {
        val operand2 = binding.txtcaja.text.toString().toDoubleOrNull()
        if (operand1 != null && operand2 != null && currentOperation != null) {
            val result = when (currentOperation) {
                "+" -> operand1!! + operand2
                "-" -> operand1!! - operand2
                "*" -> operand1!! * operand2
                "/" -> if (operand2 != 0.0) operand1!! / operand2 else Double.NaN
                else -> Double.NaN
            }
            binding.txtcaja.text.clear()
            binding.txtcaja.append(result.toString())
            currentOperation = null
            operand1 = null
        }
    }
    private fun borrar() {
        val text = binding.txtcaja.text.toString()
        if (text.isNotEmpty()) {
            binding.txtcaja.setText(text.substring(0, text.length - 1))
        }
    }
    private fun clear() {
        binding.txtcaja.text.clear()
        currentOperation = null
        operand1 = null
    }
}
