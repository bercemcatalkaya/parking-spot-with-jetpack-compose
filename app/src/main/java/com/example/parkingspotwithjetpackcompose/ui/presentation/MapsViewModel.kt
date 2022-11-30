package com.example.parkingspotwithjetpackcompose.ui.presentation

import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.parkingspotwithjetpackcompose.data.ParkingSpotRepository
import com.example.parkingspotwithjetpackcompose.data.local.ParkingSpot
import com.example.parkingspotwithjetpackcompose.ui.presentation.screen.MapEvent
import com.example.parkingspotwithjetpackcompose.ui.presentation.screen.MapState
import com.example.parkingspotwithjetpackcompose.ui.presentation.screen.MapStyle
import com.google.android.gms.maps.model.MapStyleOptions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapsViewModel @Inject constructor(
    private val repository: ParkingSpotRepository

)
: ViewModel() {
    var state by mutableStateOf(MapState())

    init {
        viewModelScope.launch {
            repository.getAllParkingSpots().collectLatest { spots ->
                state = state.copy(
                    parkingSpots = spots
                )
            }
        }
    }

    fun onEvent(event : MapEvent){
        when(event){
            is MapEvent.ToggleFalloutMap -> {
                state = state.copy(
                    properties = state.properties.copy(
                        mapStyleOptions = if(state.isFalloutView){
                            null
                        }else MapStyleOptions(MapStyle.json),
                    ),
                    isFalloutView = !state.isFalloutView
                )
            }
            is MapEvent.onMapLongClick -> {
                viewModelScope.launch {
                    repository.insertParkingSpot(
                        ParkingSpot(
                            event.latLng.latitude,
                            event.latLng.longitude)
                    )
                }
            }
            is MapEvent.onInfoWindowLongClick -> {
                viewModelScope.launch {
                    repository.deleteParkingSpot(event.spot)
                }
            }
        }
    }
}