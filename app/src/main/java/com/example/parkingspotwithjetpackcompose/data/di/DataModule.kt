package com.example.parkingspotwithjetpackcompose.data.di

import android.app.Application
import androidx.room.Room
import com.example.parkingspotwithjetpackcompose.data.ParkingSpotRepository
import com.example.parkingspotwithjetpackcompose.data.local.ParkingSpotDao
import com.example.parkingspotwithjetpackcompose.data.local.ParkingSpotDatabase
import com.example.parkingspotwithjetpackcompose.data.local.ParkingSpotDatabaseConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    @Singleton
    fun provideParkingSpotDatabase(app: Application): ParkingSpotDatabase =
        Room.databaseBuilder(app, ParkingSpotDatabase::class.java, ParkingSpotDatabaseConstants.NAME)
            .addMigrations()
            .build()

    @Provides
    @Singleton
    fun provideParkingSpotDao(db: ParkingSpotDatabase): ParkingSpotDao = db.parkingSpotDao()

    @Provides
    @Singleton
    fun provideParkingSpotRepository(dao: ParkingSpotDao): ParkingSpotRepository = ParkingSpotRepository(dao)
}