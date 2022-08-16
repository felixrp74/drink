package com.example.drinks.domain

import com.example.drinks.data.model.Drink
import com.example.drinks.data.model.ListDrinks
import com.example.drinks.vo.Resource
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {

    @GET("search.php?s=margarita")
    suspend fun getDrinkByName(@Query("tragoName") tragoName:String): ListDrinks



}