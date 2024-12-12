package com.aaf.chegaai.data.model
/**
 * Created by Andréa Fonsêca on 11/12/2024
 * Copyright (c) 2024 Andréa A. Fonsêca
 * Licensed under the MIT License.
 * See LICENSE file for details.
 **/
data class RideEstimateRequestDTO(
    val customer_id: String,
    val origin: String,
    val destination: String,
)