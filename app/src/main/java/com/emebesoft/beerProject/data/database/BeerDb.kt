package com.emebesoft.beerProject.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.emebesoft.beerProject.data.database.dao.BeerDao
import com.emebesoft.beerProject.data.database.entity.BeerEntity

@Database(entities = [BeerEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class BeerDb: RoomDatabase() {

    abstract fun getBeerDbDao(): BeerDao
}