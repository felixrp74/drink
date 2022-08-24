package com.example.drinks.data.model

import com.example.drinks.AppDatabase
import com.example.drinks.domain.DataSource
import com.example.drinks.vo.Resource
import com.example.drinks.vo.RetrofitClient

class DataSourceImpl(private val appDatabase: AppDatabase) : DataSource {

    override suspend fun getDrinkByName(drinkName:String):Resource<List<Drink>>{
        return Resource.Success(RetrofitClient.webservice.getDrinkByName(drinkName).drinkList)
    }

    override suspend fun insertFavoriteDrinkIntoRoom(drinkEntity: DrinkEntity){
        appDatabase.drinkDao().insertFavoriteDrinkIntoRoom(drinkEntity)
    }

    override suspend fun getFavoriteDrinks():Resource<List<DrinkEntity>>{
        return Resource.Success(appDatabase.drinkDao().getAllFavoriteDrinksFromRoom())
    }

    override fun deleteFavoriteDrinkFromRoom(drinkEntity: DrinkEntity) {
        appDatabase.drinkDao().deleteFavoriteDrink(drinkEntity)
    }
}