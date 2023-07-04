package com.emebesoft.beerProject.data.repository

import com.emebesoft.beerProject.data.model.BeerModel
import kotlinx.coroutines.flow.Flow

interface BeerRepository {

    suspend fun fetchBeers(): Flow<List<BeerModel>>
}