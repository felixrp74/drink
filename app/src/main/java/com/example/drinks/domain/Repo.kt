package com.example.drinks.domain

import com.example.drinks.data.model.Drink
import com.example.drinks.vo.Resource

interface Repo {
    suspend fun getDrinkList(drinkName:String): Resource<List<Drink>>
}