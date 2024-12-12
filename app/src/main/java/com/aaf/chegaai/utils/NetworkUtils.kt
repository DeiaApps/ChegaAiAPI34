package com.aaf.chegaai.utils

import com.google.gson.JsonObject
import com.google.gson.JsonParser

/**
 * Created by Andréa Fonsêca on 10/12/2024
 * Copyright (c) 2024 Andréa A. Fonsêca
 * Licensed under the MIT License.
 * See LICENSE file for details.
 **/

fun parseErrorJson(errorBody: String?): JsonObject? {
    return try {
        errorBody?.let {
            val jsonElement = JsonParser.parseString(it)
            if (jsonElement.isJsonObject) jsonElement.asJsonObject else null
        }
    } catch (e: Exception) {
        null
    }
}