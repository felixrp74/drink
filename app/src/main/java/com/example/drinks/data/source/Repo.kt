package com.example.drinks.data.source

import com.example.drinks.data.model.Drink
import com.example.drinks.data.model.DrinkEntity
import com.example.drinks.data.vo.Resource

interface Repo {
    suspend fun getDrinkList(drinkName:String): Resource<List<Drink>>
    suspend fun getFavoriteDrinkList(): Resource<List<DrinkEntity>>
    suspend fun insertFavoriteDrink(drinkEntity: DrinkEntity)
    suspend fun deleteFavoriteDrink(drinkEntity: DrinkEntity)
}