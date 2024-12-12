package com.aaf.chegaai.domain.usecase
/**
 * Created by Andréa Fonsêca on 11/12/2024
 * Copyright (c) 2024 Andréa A. Fonsêca
 * Licensed under the MIT License.
 * See LICENSE file for details.
 **/

data class ValidationResult (
    var customerId: Boolean = false,
    var origin: Boolean = false,
    var destination: Boolean = false
){
    val completedForm: Boolean
        get() = customerId && origin && destination
}