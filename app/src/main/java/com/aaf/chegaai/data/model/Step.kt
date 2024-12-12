package com.aaf.chegaai.data.model

data class Step(
    val distanceMeters: Int,
    val endLocation: EndLocation,
    val localizedValues: LocalizedValuesX,
    val navigationInstruction: NavigationInstruction,
    val polyline: PolylineXX,
    val startLocation: StartLocation,
    val staticDuration: String,
    val travelMode: String
)