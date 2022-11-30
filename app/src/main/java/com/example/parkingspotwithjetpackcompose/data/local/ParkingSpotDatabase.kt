package com.example.parkingspotwithjetpackcompose.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [ParkingSpot::class],
    version = ParkingSpotDatabaseConstants.VERSION
)
abstract class ParkingSpotDatabase : RoomDatabase() {
    abstract fun parkingSpotDao(): ParkingSpotDao
}