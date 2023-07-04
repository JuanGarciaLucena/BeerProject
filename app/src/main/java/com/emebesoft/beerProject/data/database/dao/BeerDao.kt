package com.emebesoft.beerProject.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.emebesoft.beerProject.data.database.entity.BeerEntity

@Dao
interface BeerDao {

    @Query("SELECT * FROM beer_table")
    suspend fun getAll(): List<BeerEntity>
}