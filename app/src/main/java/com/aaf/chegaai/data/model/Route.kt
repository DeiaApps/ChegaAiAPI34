package com.aaf.chegaai.data.model

data class Route(
    val description: String,
    val distanceMeters: Int,
    val duration: String,
    val legs: List<Leg>,
    val localizedValues: LocalizedValuesXX,
    val polyline: PolylineXX,
    val polylineDetails: PolylineDetails,
    val routeLabels: List<String>,
    val staticDuration: String,
    val travelAdvisory: TravelAdvisory,
    val viewport: Viewport,
    val warnings: List<String>
)