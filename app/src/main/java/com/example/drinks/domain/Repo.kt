package com.example.drinks.domain

import com.example.drinks.data.model.Drink
import com.example.drinks.data.model.DrinkEntity
import com.example.drinks.vo.Resource

interface Repo {
    suspend fun getDrinkList(drinkName:String): Resource<List<Drink>>
    suspend fun getFavoriteDrinkList(): Resource<List<DrinkEntity>>
    suspend fun insertFavoriteDrink(drinkEntity: DrinkEntity)
}