package com.aaf.chegaai.domain.model
/**
 * Created by Andréa Fonsêca on 08/12/2024
 * Copyright (c) 2024 Andréa A. Fonsêca
 * Licensed under the MIT License.
 * See LICENSE file for details.
 **/
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class Driver(
    val id: Int,
    val name: String
) : Parcelable
