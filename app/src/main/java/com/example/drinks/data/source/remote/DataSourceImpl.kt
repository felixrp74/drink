package com.example.drinks.data.source.remote

import com.example.drinks.data.vo.AppDatabase
import com.example.drinks.data.model.Drink
import com.example.drinks.data.model.DrinkEntity
import com.example.drinks.data.vo.Resource
import com.example.drinks.data.vo.RetrofitClient

class DataSourceImpl(private val appDatabase: AppDatabase) : DataSource {

    override suspend fun getDrinkByName(drinkName:String): Resource<List<Drink>> {
        return Resource.Success(RetrofitClient.webservice.getDrinkByName(drinkName).drinkList)
    }

    override suspend fun insertFavoriteDrinkIntoRoom(drinkEntity: DrinkEntity){
        appDatabase.drinkDao().insertFavoriteDrinkIntoRoom(drinkEntity)
    }

    override suspend fun getFavoriteDrinks(): Resource<List<DrinkEntity>> {
        return Resource.Success(appDatabase.drinkDao().getAllFavoriteDrinksFromRoom())
    }

    override fun deleteFavoriteDrinkFromRoom(drinkEntity: DrinkEntity) {
        appDatabase.drinkDao().deleteFavoriteDrink(drinkEntity)
    }
}