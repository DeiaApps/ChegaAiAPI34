package com.aaf.chegaai.data.model

data class RouteResponse(
    val geocodingResults: GeocodingResults,
    val routes: List<Route>
)