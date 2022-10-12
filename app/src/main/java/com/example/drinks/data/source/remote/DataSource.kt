package com.example.drinks.data.source.remote

import com.example.drinks.data.model.Drink
import com.example.drinks.data.model.DrinkEntity
import com.example.drinks.data.vo.Resource

interface DataSource {
    suspend fun getDrinkByName(drinkName:String): Resource<List<Drink>>
    suspend fun insertFavoriteDrinkIntoRoom(drinkEntity: DrinkEntity)
    suspend fun getFavoriteDrinks(): Resource<List<DrinkEntity>>
    fun deleteFavoriteDrinkFromRoom(drinkEntity: DrinkEntity)
}