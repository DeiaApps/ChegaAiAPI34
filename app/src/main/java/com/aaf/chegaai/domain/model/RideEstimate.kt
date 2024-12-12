package com.aaf.chegaai.domain.model
/**
 * Created by Andréa Fonsêca on 10/12/2024
 * Copyright (c) 2024 Andréa A. Fonsêca
 * Licensed under the MIT License.
 * See LICENSE file for details.
 **/

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RideEstimate(
    val destination: String,
    val distance: Int,
    val duration: Int,
    val options: List<RideOption>,
    val origin: String,
    val route: String,
) : Parcelable

