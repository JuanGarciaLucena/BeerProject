package com.emebesoft.beerProject.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.emebesoft.beerProject.data.database.entity.BeerEntity

@Dao
interface BeerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveBeerList(characterList: List<BeerEntity>)

    @Query("SELECT * FROM beer_table")
    suspend fun getBeerList(): List<BeerEntity>
}