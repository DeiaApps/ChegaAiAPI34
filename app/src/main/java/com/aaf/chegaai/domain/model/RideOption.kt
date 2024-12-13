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
data class RideOption(
    val driverDescription: String,
    val driverId: Int,
    val driverName: String,
    val driverNote: Int,
    val driverReviews: String,
    val tripValue: Double,
    val vehicle: String
) : Parcelable
