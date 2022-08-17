package com.example.drinks.domain

import com.example.drinks.data.model.ListDrinks
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {
    @GET("search.php")
    suspend fun getDrinkByName(@Query(value = "s") tragoName:String): ListDrinks
}