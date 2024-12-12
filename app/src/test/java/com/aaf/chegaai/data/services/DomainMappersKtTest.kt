package com.aaf.chegaai.data.services

import com.aaf.chegaai.data.model.Destination
import com.aaf.chegaai.data.model.DestinationX
import com.aaf.chegaai.data.model.GeocoderStatus
import com.aaf.chegaai.data.model.GeocodingResults
import com.aaf.chegaai.data.model.Option
import com.aaf.chegaai.data.model.Origin
import com.aaf.chegaai.data.model.OriginX
import com.aaf.chegaai.data.model.Review
import com.aaf.chegaai.data.model.RideEstimateDTO
import com.aaf.chegaai.data.model.Route
import com.aaf.chegaai.data.model.RouteResponse
import org.junit.Assert.*

import org.junit.Test

/**
 * Created by Andréa Fonsêca on 11/12/2024
 * Copyright (c) 2024 Andréa A. Fonsêca
 * Licensed under the MIT License.
 * See LICENSE file for details.
 */
class DomainMappersKtTest {

    /*@Test
    fun `test RideEstimateDTO to RideEstimate mapping`() {
        val dto = RideEstimateDTO(
            destination = Destination(-23.5,-46.6),
            distance = 1000,
            duration = 600,
            options = listOf(Option( "Rua X", 1,  "João", Review("fsskj", 4)50.05,"Carro X")),
            origin = Origin(latitude = -23.6, longitude = -46.7),
            routeResponse = RouteResponse( GeocodingResults(), listOf<Route>("rotas","fsfsd"))
        )*/

///*        val domain = dto.toDomain()
//
//        assertEquals("Rua X", domain.destination)
//        assertEquals(1000, domain.distance)
//        assertEquals(600, domain.duration)
//        assertEquals(1, domain.options.size)
//        assertEquals("João", domain.options[0].driverName)
//    }*/
}