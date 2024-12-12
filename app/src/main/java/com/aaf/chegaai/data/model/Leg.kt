package com.aaf.chegaai.data.model

data class Leg(
    val distanceMeters: Int,
    val duration: String,
    val endLocation: EndLocation,
    val localizedValues: LocalizedValues,
    val polyline: PolylineXX,
    val startLocation: StartLocation,
    val staticDuration: String,
    val steps: List<Step>
)