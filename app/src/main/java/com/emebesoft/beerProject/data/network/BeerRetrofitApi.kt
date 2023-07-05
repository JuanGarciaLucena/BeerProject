package com.emebesoft.beerProject.data.network

import com.emebesoft.beerProject.data.model.BeerModel
import retrofit2.http.GET
import retrofit2.http.Query

interface BeerRetrofitApi {

    @GET("beers?page=1&per_page=10")
    suspend fun fetchBeers() : List<BeerModel>

    @GET("beers")
    suspend fun searchBeers(@Query("beer_name") beerQuery: String): List<BeerModel>

    @GET("beers")
    suspend fun searchBeerById(@Query("ids") beerId: String) : List<BeerModel>
}