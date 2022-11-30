package com.example.parkingspotwithjetpackcompose.ui.presentation.screen
import com.example.parkingspotwithjetpackcompose.data.local.ParkingSpot
import com.google.maps.android.compose.MapProperties

data class MapState(
    val properties : MapProperties = MapProperties(),
    val parkingSpots : List <ParkingSpot> = emptyList(),
    val isFalloutView : Boolean = false
) {
}