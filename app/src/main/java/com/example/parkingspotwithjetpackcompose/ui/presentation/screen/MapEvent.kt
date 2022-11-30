package com.example.parkingspotwithjetpackcompose.ui.presentation.screen

import com.example.parkingspotwithjetpackcompose.data.local.ParkingSpot
import com.google.android.gms.maps.model.LatLng

sealed class MapEvent {
    object ToggleFalloutMap : MapEvent()
    data class onMapLongClick(var latLng : LatLng) : MapEvent()
    data class onInfoWindowLongClick(val spot : ParkingSpot) : MapEvent()
}
