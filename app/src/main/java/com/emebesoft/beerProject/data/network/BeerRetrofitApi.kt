package com.emebesoft.beerProject.data.network

import com.emebesoft.beerProject.data.model.BeerModel
import retrofit2.http.GET

interface BeerRetrofitApi {

    @GET("beers?page=1&per_page=10")
    suspend fun fetchBeers() : List<BeerModel>
}