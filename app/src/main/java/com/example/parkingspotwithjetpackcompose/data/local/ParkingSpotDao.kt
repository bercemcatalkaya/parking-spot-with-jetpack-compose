package com.example.parkingspotwithjetpackcompose.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ParkingSpotDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertParkingSpot(spot : ParkingSpot)

    @Delete
    suspend fun deleteParkingSpot(spot: ParkingSpot)

    @Query("SELECT * FROM ${ParkingSpotDatabaseConstants.TABLE_PARKING_SPOT}")
    fun getAllParkingSpots(): Flow<List<ParkingSpot>>

}