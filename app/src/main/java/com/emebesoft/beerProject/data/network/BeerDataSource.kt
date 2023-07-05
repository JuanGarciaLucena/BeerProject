package com.emebesoft.beerProject.data.network

import javax.inject.Inject

class BeerDataSource @Inject constructor(private val api: BeerRetrofitApi) {

    suspend fun fetchBeersFromApi() = api.fetchBeers()
    suspend fun searchBeers(beerQuery: String) = api.searchBeers(beerQuery)
    suspend fun searchBeerById(beerId: String) = api.searchBeerById(beerId)
}