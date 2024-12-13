package com.aaf.chegaai.utils
/**
 * Created by Andréa Fonsêca on 12/12/2024
 * Copyright (c) 2024 Andréa A. Fonsêca
 * Licensed under the MIT License.
 * See LICENSE file for details.
 **/

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import java.text.NumberFormat
import java.util.Locale
import kotlin.math.pow

/*
addCoinMask - R$ 19,90
moedaToDouble - R$ 19,90 PARA 19.90
formatarComoMoeda - 19.9 PARA R$ 19,90
* */
fun EditText.addCoinMask(
    local: Locale = Locale("pt", "BR"),
    maxDecimalDigits: Int = 2,
    //maximoDigitosDecimais: Int = 2,
    customCoinSymbol: String = "R$",
    maxDigits: Int = 7
) {
    var currentText = ""

    val currencyFormat = NumberFormat.getCurrencyInstance(local).apply {
        maximumFractionDigits = maxDecimalDigits
    }

    val watcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            // Nenhuma ação necessária aqui
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            // Nenhuma ação necessária aqui
        }

        override fun afterTextChanged(s: Editable?) {
            if (s.toString() != currentText) {
                removeTextChangedListener(this)

                // Remove caracteres não numéricos
                val cleanString = s.toString().replace("\\D".toRegex(), "")

                // Limita o número máximo de dígitos conforme definido em maxDigits
                val limitedString = if (maxDigits != null && cleanString.length > maxDigits) {
                    cleanString.take(maxDigits)
                } else {
                    cleanString
                }

                // Converte para decimal
                val parsed = limitedString.toDoubleOrNull()?.div(10.0.pow(maxDecimalDigits.toDouble())) ?: 0.0

                // Aplica o símbolo customizado, se fornecido
                val currencySymbol = customCoinSymbol ?: currencyFormat.currency?.symbol ?: ""

                // Formata o valor com o símbolo sempre antes do número
                var formatted = currencyFormat.format(parsed)
                formatted = "$currencySymbol ${formatted.replace(currencyFormat.currency?.symbol ?: "", "").trim()}"

                currentText = formatted
                setText(formatted)
                setSelection(formatted.length)

                addTextChangedListener(this)
            }
        }
    }

    // Adiciona o TextWatcher ao EditText
    this.addTextChangedListener(watcher)
}

// Função para obter o valor atual como Double, acessível em qualquer EditText
fun EditText.moedaToDouble(maxDecimalDigits: Int = 2): Double {
    val cleanString = this.text.toString().replace("\\D".toRegex(), "")
    return cleanString.toDoubleOrNull()?.div(10.0.pow(maxDecimalDigits.toDouble())) ?: 0.0
}

// Função para formatar um Double como moeda
fun Double.formatAsCoin(
    local: Locale = Locale("pt", "BR"),
    maxDecimalDigits: Int = 2,
    customCoinSymbol: String = "R$"
): String {
    val currencyFormat = NumberFormat.getCurrencyInstance(local).apply {
        maximumFractionDigits = maxDecimalDigits
    }

    // Aplica o símbolo customizado, se fornecido
    val currencySymbol = customCoinSymbol ?: currencyFormat.currency?.symbol ?: ""

    // Formata o valor com o símbolo sempre antes do número
    val formatted = currencyFormat.format(this)
    return "$currencySymbol ${formatted.replace(currencyFormat.currency?.symbol ?: "", "").trim()}"
}