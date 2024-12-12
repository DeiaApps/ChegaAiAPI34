package com.aaf.chegaai.utils
/**
 * Created by Andréa Fonsêca on 08/12/2024
 * Copyright (c) 2024 Andréa A. Fonsêca
 * Licensed under the MIT License.
 * See LICENSE file for details.
 **/

sealed class RideResult<out T> {
    object Loading : RideResult<Nothing>()
    data class Success<out T>(val data: T) : RideResult<T>()
    data class Error(val errorCode: String, val errorDescription: String) : RideResult<Nothing>()
}