package com.emebesoft.beerProject.data.repository

import com.emebesoft.beerProject.data.model.BeerModel
import com.emebesoft.beerProject.data.network.BeerDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BeerRepositoryImpl @Inject constructor(
    private val beerDataSource: BeerDataSource
) : BeerRepository {


    override suspend fun fetchBeers() : Flow<List<BeerModel>> {
        return flow {
            emit(beerDataSource.fetchBeersFromApi())
        }
    }

    override suspend fun searchBeer(beerQuery: String): Flow<List<BeerModel>> {
        return flow {
            emit(beerDataSource.searchBeers(beerQuery))
        }
    }

    override suspend fun searchBeerById(beerId: String): Flow<List<BeerModel>> {
        return flow {
            emit(beerDataSource.searchBeerById(beerId))
        }
    }
}