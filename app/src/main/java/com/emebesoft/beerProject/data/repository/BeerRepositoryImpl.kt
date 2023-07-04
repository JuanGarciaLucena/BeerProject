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


        val paco = flow {
            emit(beerDataSource.fetchBeersFromApi())
        }




        return paco
    }
}