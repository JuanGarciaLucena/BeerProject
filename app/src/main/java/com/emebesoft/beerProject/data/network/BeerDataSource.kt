package com.emebesoft.beerProject.data.network

import javax.inject.Inject

class BeerDataSource @Inject constructor(private val api: BeerRetrofitApi) {

    suspend fun fetchBeersFromApi() = api.fetchBeers()
}