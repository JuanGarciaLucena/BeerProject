package com.emebesoft.beerProject.data.repository

import com.emebesoft.beerProject.data.model.BeerModel
import kotlinx.coroutines.flow.Flow

interface BeerRepository {

    suspend fun fetchBeers(): Flow<List<BeerModel>>
    suspend fun searchBeer(beerQuery: String): Flow<List<BeerModel>>
    suspend fun searchBeerById(beerId: String) : Flow<List<BeerModel>>
}