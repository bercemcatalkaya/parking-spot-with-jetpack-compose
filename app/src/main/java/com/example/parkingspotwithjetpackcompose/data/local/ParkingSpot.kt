package com.example.parkingspotwithjetpackcompose.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ParkingSpot(
    val latitude: Double,
    val longitude: Double,
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
)