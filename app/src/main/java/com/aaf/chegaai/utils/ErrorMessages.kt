package com.aaf.chegaai.utils

import com.google.gson.JsonObject
import com.google.gson.JsonParser

/**
 * Created by Andréa Fonsêca on 09/12/2024
 * Copyright (c) 2024 Andréa A. Fonsêca
 * Licensed under the MIT License.
 * See LICENSE file for details.
 **/

object ErrorMessages {
    private val errorMap = mapOf(
        "INVALID_DATA" to "Os dados fornecidos são inválidos. Verifique-os e tente novamente.",
        "DRIVER_NOT_FOUND" to "Motorista não encontrado. Tente outro motorista",
        "INVALID_DISTANCE" to "Quilometragem inválida para o motorista. Tente outro motorista",
        "NO_RIDES_FOUND" to "Nenhuma viagem encontrado. Por favor, tente novamente.",
        "INVALID_DRIVER" to "Motorista inválido. Verifique os dados e tente novamente.",

        "INVALID_CUSTOMER" to "Cliente não encontrado. Verifique os dados fornecidos.",
        "RIDE_NOT_FOUND" to "Corrida não encontrada. Por favor, tente novamente.",

        "SERVER_ERROR" to "Erro interno no servidor. Tente novamente mais tarde.",

/*        // Mensagens gerais que podem ser usadas em casos genéricos
        "UNKNOWN_ERROR" to "Ocorreu um erro desconhecido. Tente novamente.",
        "BAD_REQUEST" to "Requisição inválida. Tente novamente mais tarde."*/
    )

    fun getMessage(errorCode: String?, errorDescription: String?): String {
       /* // Se a API fornece uma descrição mais detalhada do erro, usamos ela
        if (!errorDescription.isNullOrBlank()) return errorDescription
        // Caso contrário, usamos uma mensagem genérica com base no código do erro
        return errorMap[errorCode] ?: errorMap["UNKNOWN_ERROR"] ?: "Erro desconhecido. Tente novamente."
        */
        return errorDescription ?: errorMap[errorCode] ?: "Erro desconhecido. Tente novamente."
    }
}
