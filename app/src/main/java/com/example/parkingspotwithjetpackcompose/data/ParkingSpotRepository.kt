package com.example.parkingspotwithjetpackcompose.data

import com.example.parkingspotwithjetpackcompose.data.local.ParkingSpot
import com.example.parkingspotwithjetpackcompose.data.local.ParkingSpotDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class ParkingSpotRepository(
    private val dao: ParkingSpotDao
) {
    suspend fun insertParkingSpot(spot: ParkingSpot) {
        dao.insertParkingSpot(spot)
    }

    suspend fun deleteParkingSpot(spot: ParkingSpot) {
        dao.deleteParkingSpot(spot)
    }

    fun getAllParkingSpots(): Flow<List<ParkingSpot>> {
        return dao.getAllParkingSpots()
    }
}