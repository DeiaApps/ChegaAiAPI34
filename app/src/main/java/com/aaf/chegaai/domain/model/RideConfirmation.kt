package com.aaf.chegaai.domain.model
/**
 * Created by Andréa Fonsêca on 12/12/2024
 * Copyright (c) 2024 Andréa A. Fonsêca
 * Licensed under the MIT License.
 * See LICENSE file for details.
 **/
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RideConfirmation(
    val customerId: String,
    val destination: String,
    val duration: String,
    val driver: Driver,//Drive
    val origin: String,
    val distance: Int,
    val value: Double
) : Parcelable

