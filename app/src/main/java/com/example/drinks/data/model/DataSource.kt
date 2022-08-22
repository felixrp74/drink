package com.example.drinks.data.model

import com.example.drinks.AppDatabase
import com.example.drinks.vo.Resource
import com.example.drinks.vo.RetrofitClient

class DataSource(private val appDatabase: AppDatabase) {

    suspend fun getTragoByName(drinkName:String):Resource<List<Drink>>{
        return Resource.Success(RetrofitClient.webservice.getDrinkByName(drinkName).drinkList)
    }

    suspend fun insertFavoriteDrinkIntoRoom(drinkEntity: DrinkEntity){
        appDatabase.drinkDao().insertFavoriteDrinkIntoRoom(drinkEntity)
    }

    suspend fun getFavoriteDrinks():Resource<List<DrinkEntity>>{
        return Resource.Success(appDatabase.drinkDao().getAllFavoriteDrinksFromRoom())
    }
}