package com.example.drinks.data.model

import com.example.drinks.vo.Resource
import com.example.drinks.vo.RetrofitClient

class DataSource {

    suspend fun getTragoByName(drinkName:String):Resource<List<Drink>>{
        return Resource.Success(RetrofitClient.webservice.getDrinkByName(drinkName).drinkList)
    }
}