package com.aaf.chegaai.domain.model
/**
 * Created by Andréa Fonsêca on 10/12/2024
 * Copyright (c) 2024 Andréa A. Fonsêca
 * Licensed under the MIT License.
 * See LICENSE file for details.
 **/

data class RideEstimateRequest(
    val customerId: String,
    val origin: String,
    val destination: String
)
