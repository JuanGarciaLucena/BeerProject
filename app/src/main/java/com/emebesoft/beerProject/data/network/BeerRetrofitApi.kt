package com.emebesoft.beerProject.data.network

import com.emebesoft.beerProject.data.model.BeerModel
import retrofit2.http.GET

interface BeerRetrofitApi {

    @GET("/beers")
    suspend fun fetchBeers() : List<BeerModel>
}